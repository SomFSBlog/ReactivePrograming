package com.syf.api.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.syf.api.model.CustomerDetails;

@RestController
@RequestMapping("reactiveApi")
@CrossOrigin
public class RestNonReactiveController {
	private static final Logger log = LoggerFactory.getLogger(RestNonReactiveController.class);
	final String customerDetailsURI = "http://localhost:8080/reactiveApi/customer-service-info/";

	@GetMapping("/blocking/{count}")
	private List<String> getBlockingResponse(@PathVariable int count) throws InterruptedException {
		log.info("Blocking Response Start Time::{} " + LocalDateTime.now());
		log.info("Starting Blocking Controller!");
		List<String> customerList = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate();
		List<Integer> countList = IntStream.rangeClosed(1, count).boxed().collect(Collectors.toList());
		countList.forEach(i -> restTemplate.exchange(customerDetailsURI + i, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<CustomerDetails>>() {

				}).getBody().forEach(records -> {
					customerList.add(records.toString());
				}));
		log.info("Exiting Blocking Controller!");
		return customerList;

	}

}
