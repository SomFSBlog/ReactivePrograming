package com.syf.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.syf.api.model.CustomerDetails;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("reactiveApi")
@CrossOrigin
public class RestReactiveController {
	private static final Logger log = LoggerFactory.getLogger(RestReactiveController.class);
	final String customerDetailsURI = "http://localhost:8080/reactiveApi/customer-service-info/";

	@GetMapping(value = "/non-blocking/{count}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	private Flux<CustomerDetails> getNonBlockingResponse(@PathVariable int count) throws InterruptedException {
		log.info("Blocking Response Start Time::{} " + LocalDateTime.now());
		log.info("Starting Non-Blocking Controller!");

		List<Integer> countList = IntStream.rangeClosed(1, count).boxed().collect(Collectors.toList());

		Flux<CustomerDetails> customerDetailsFlux = Flux.fromIterable(countList).flatMap(item -> WebClient.create()
				.get().uri(customerDetailsURI + item).retrieve().bodyToFlux(CustomerDetails.class));

		log.info("Exiting Non-Blocking Controller!");
		return customerDetailsFlux;

	}

}
