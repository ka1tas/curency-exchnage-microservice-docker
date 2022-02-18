package com.kaitas.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kaitas.currencyexchangeservice.bean.CurrencyExchange;
import com.kaitas.currencyexchangeservice.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment env;

	@Autowired
	private CurrencyExchangeService service;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveCurencyExchange(@PathVariable String from, @PathVariable String to) {

		String port = env.getProperty("local.server.port");
		CurrencyExchange currencyExchange = service.findCurencyExchange(from, to);
		if (currencyExchange == null) {
			throw new RuntimeException("Currency exchange rate not found for " + from + " to " + to + ".");
		}
		currencyExchange.setEnvironment(port);

		return currencyExchange;
	}

}
