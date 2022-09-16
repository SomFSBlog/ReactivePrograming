package com.syf.api.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syf.api.model.CustomerDetails;

@RestController
@RequestMapping("reactiveApi")
public class ApplicationController {

	private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

	@GetMapping("/customer-service-info/{i}")
	private List<CustomerDetails> getCustomerDetails(@PathVariable int i) throws InterruptedException {
		log.info("count item::: " + i);
		Thread.sleep(1000);
		if (validateRequest(i)) {
			return Arrays.asList(
					new CustomerDetails("Name" + i, "CUST_ID_000" + i, (long) (1000000000 + i), LocalDateTime.now()));
		}
		return null;

	}

	private boolean validateRequest(int i) throws InterruptedException {
		Thread.sleep(1000);
		if (i >= 0) {
			return true;
		}
		return false;
	}

}
