package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.hello.HelloRequest;
import proto.hello.HelloResponse;
import proto.hello.HelloServiceGrpc;
import proto.hello.HelloServiceGrpc.HelloServiceBlockingStub;

@Controller
public class HelloController {
	private final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8081)
		.usePlaintext()
		.build();
	private final HelloServiceBlockingStub blockingStub = HelloServiceGrpc.newBlockingStub(channel);

	@GetMapping("/")
	public String btn() { return "index"; }

	@GetMapping("/hello")
	public String greeting(@RequestParam String name, Model model) {
		HelloRequest request = HelloRequest.newBuilder()
			.setName(name)
			.build();

		HelloResponse response = blockingStub.hello(request);
		String responseName = response.getResponse();
		model.addAttribute("name", String.format("%s아 안녕~", responseName));
		return "hello";
	}
}

