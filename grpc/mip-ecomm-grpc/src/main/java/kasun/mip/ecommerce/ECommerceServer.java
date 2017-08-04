package kasun.mip.ecommerce;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.logging.Logger;

public class ECommerceServer {

    private static final Logger logger = Logger.getLogger(ECommerceServer.class.getName());

    private Server server;

    private void start() throws IOException {
        int port = 60061;
        server = ServerBuilder.forPort(port)
                .addService(new ECommerceServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                ECommerceServer.this.stop();
                System.err.println("*** server shut down");
            }
        });

    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    static class ECommerceServiceImpl extends ECommerceServiceGrpc.ECommerceServiceImplBase {
        @Override
        public void addProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
            ProductResponse response = ProductResponse.newBuilder()
                                            .setProductID(request.getProductID())
                                            .setStatus("Successful")
                                        .build();
            logger.info("product ID : " + request.getProductID() + ": Product Name "+ request.getName());
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final ECommerceServer server = new ECommerceServer();
        server.start();
        server.blockUntilShutdown();
    }
}
