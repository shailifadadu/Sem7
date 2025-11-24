package com.example.server;

import com.example.server.proto.HelloRequest;
import com.example.server.proto.HelloReply;
import com.example.server.proto.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//This is the entry point . Starts Spring Boot and loads all @GrpcService and @Component classes.
@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}

@GrpcService //This tells SpringBoot to expose this as a gRPC service
class GrpcServerService extends SimpleGrpc.SimpleImplBase {
	//Overrided sayHello method from proto generated base class.
	//This method receives HelloRequest from the client.
	@Override
	public void sayHello(HelloRequest req , StreamObserver<HelloReply> responseObserver) {
		//Builds a HelloReply message and sends it back using responseObserver.
		HelloReply reply = HelloReply.newBuilder().setMessage("Hello Faculty ==> " + req.getName()).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}