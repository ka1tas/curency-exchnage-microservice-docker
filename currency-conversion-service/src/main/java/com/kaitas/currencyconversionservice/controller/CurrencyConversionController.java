package com.kaitas.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kaitas.currencyconversionservice.CurrencyConversionProxy;
import com.kaitas.currencyconversionservice.bean.CurrencyConversion;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyConversionProxy proxy;

	@GetMapping("/currency-conversion-resttemp/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		HashMap<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversion> currencyResponse = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);
		CurrencyConversion currency = currencyResponse.getBody();
		currency.setQuantity(quantity);
		currency.setTotalCalculatedAmount(quantity.multiply(currency.getConversionMultiple()));
//		CurrencyConversion currency = new CurrencyConversion(11L, from, to, quantity, BigDecimal.valueOf(65.4),
//				BigDecimal.valueOf(1465.414), "LocalHost:8100");
		return currency;
	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		CurrencyConversion currency = proxy.retriveCurencyExchange(from, to);
		currency.setQuantity(quantity);
		currency.setTotalCalculatedAmount(quantity.multiply(currency.getConversionMultiple()));
//		CurrencyConversion currency = new CurrencyConversion(11L, from, to, quantity, BigDecimal.valueOf(65.4),
//				BigDecimal.valueOf(1465.414), "LocalHost:8100");
		return currency;
	}
}
