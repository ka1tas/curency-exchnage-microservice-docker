package com.kaitas.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaitas.limitservice.bean.Limits;
import com.kaitas.limitservice.configuration.Configuration;

@RestController
public class LimitController {

	@Autowired
	private Configuration config;

	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(config.getMinimum(), config.getMaximum());
	}

}
