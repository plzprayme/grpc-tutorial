package service

import io.grpc.stub.StreamObserver
import proto.hello.HelloRequest
import proto.hello.HelloResponse
import proto.hello.HelloServiceGrpc

class HelloGrpcService : HelloServiceGrpc.HelloServiceImplBase() {
    override fun hello(
        request: HelloRequest,
        responseObserver: StreamObserver<HelloResponse>
    ) {
        val response = request.toResponse()
        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

    private fun HelloRequest.toResponse() : HelloResponse {
        return HelloResponse.newBuilder()
            .setResponse(name)
            .build()
    }
}