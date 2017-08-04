package kasun.mip.ecommerce;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: ecomm_service.proto")
public final class ECommerceServiceGrpc {

  private ECommerceServiceGrpc() {}

  public static final String SERVICE_NAME = "ecommerce.ECommerceService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<kasun.mip.ecommerce.ProductRequest,
      kasun.mip.ecommerce.ProductResponse> METHOD_ADD_PRODUCT =
      io.grpc.MethodDescriptor.<kasun.mip.ecommerce.ProductRequest, kasun.mip.ecommerce.ProductResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ecommerce.ECommerceService", "AddProduct"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kasun.mip.ecommerce.ProductRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              kasun.mip.ecommerce.ProductResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ECommerceServiceStub newStub(io.grpc.Channel channel) {
    return new ECommerceServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ECommerceServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ECommerceServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ECommerceServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ECommerceServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ECommerceServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void addProduct(kasun.mip.ecommerce.ProductRequest request,
        io.grpc.stub.StreamObserver<kasun.mip.ecommerce.ProductResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ADD_PRODUCT, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ADD_PRODUCT,
            asyncUnaryCall(
              new MethodHandlers<
                kasun.mip.ecommerce.ProductRequest,
                kasun.mip.ecommerce.ProductResponse>(
                  this, METHODID_ADD_PRODUCT)))
          .build();
    }
  }

  /**
   */
  public static final class ECommerceServiceStub extends io.grpc.stub.AbstractStub<ECommerceServiceStub> {
    private ECommerceServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ECommerceServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ECommerceServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ECommerceServiceStub(channel, callOptions);
    }

    /**
     */
    public void addProduct(kasun.mip.ecommerce.ProductRequest request,
        io.grpc.stub.StreamObserver<kasun.mip.ecommerce.ProductResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ADD_PRODUCT, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ECommerceServiceBlockingStub extends io.grpc.stub.AbstractStub<ECommerceServiceBlockingStub> {
    private ECommerceServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ECommerceServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ECommerceServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ECommerceServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public kasun.mip.ecommerce.ProductResponse addProduct(kasun.mip.ecommerce.ProductRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ADD_PRODUCT, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ECommerceServiceFutureStub extends io.grpc.stub.AbstractStub<ECommerceServiceFutureStub> {
    private ECommerceServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ECommerceServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ECommerceServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ECommerceServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<kasun.mip.ecommerce.ProductResponse> addProduct(
        kasun.mip.ecommerce.ProductRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ADD_PRODUCT, getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_PRODUCT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ECommerceServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ECommerceServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_PRODUCT:
          serviceImpl.addProduct((kasun.mip.ecommerce.ProductRequest) request,
              (io.grpc.stub.StreamObserver<kasun.mip.ecommerce.ProductResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ECommerceServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return kasun.mip.ecommerce.EcommerceProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ECommerceServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ECommerceServiceDescriptorSupplier())
              .addMethod(METHOD_ADD_PRODUCT)
              .build();
        }
      }
    }
    return result;
  }
}
