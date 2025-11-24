package com.example.client;

import com.example.client.proto.HelloRequest;
import com.example.client.proto.SimpleGrpc;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import net.devh.boot.grpc.client.inject.GrpcClient;


@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

@Bean
ApplicationRunner clientRunner(@GrpcClient("helloService") SimpleGrpc.SimpleBlockingStub simpleBlockingStub){
	return new ApplicationRunner(){
		@Override
		public void run(ApplicationArguments args) throws Exception {
			System.out.println(simpleBlockingStub.sayHello(HelloRequest.newBuilder().setName("Vipul Dabhi").build()));
		}
	};
}
}