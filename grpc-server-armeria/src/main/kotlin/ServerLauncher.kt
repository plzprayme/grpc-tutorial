import com.linecorp.armeria.server.Server
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.grpc.GrpcService
import service.HelloGrpcService

fun main() {
    Server.builder().apply {
        http(8081)
        service(getGrpcService())
        serviceUnder("/docs", DocService())
    }
        .build()
        .start()
}

private fun getGrpcService(): GrpcService {
    return GrpcService.builder()
        .addService(HelloGrpcService())
        .build()
}