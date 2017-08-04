package kasun.mip.ecommerce;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ECommerceClient {

    private static final Logger logger = Logger.getLogger(ECommerceClient.class.getName());
    private final ManagedChannel channel;

    private final ECommerceServiceGrpc.ECommerceServiceBlockingStub blockingStub;


    public ECommerceClient(String host, int port) {

        this(ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true));
    }


    ECommerceClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = ECommerceServiceGrpc.newBlockingStub(channel);
    }


    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public ProductResponse addProduct(String productID, String productName, String productDesc) {
        ProductRequest request = ProductRequest.newBuilder()
                .setProductID(productID)
                .setName(productName)
                .setDescription(productDesc)
                .build();

        ProductResponse response = null;
        try {
            response = blockingStub.addProduct(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
        }
        return response;
    }


    public static void main(String[] args) throws Exception {

        ECommerceClient client = new ECommerceClient("localhost", 60061);
        ProductResponse response = client.addProduct("1234565", "Apple iPhone", "iPhone 7s, designed in CA, made in China.");
        logger.info("Response : ID - " + response.getProductID() + ", Status - "+ response.getStatus());
    }
}
