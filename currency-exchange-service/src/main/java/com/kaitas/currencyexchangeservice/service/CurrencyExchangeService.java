package com.kaitas.currencyexchangeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaitas.currencyexchangeservice.bean.CurrencyExchange;
import com.kaitas.currencyexchangeservice.repository.CurrencyExchangeRepository;

@Service
public class CurrencyExchangeService {

	@Autowired
	private CurrencyExchangeRepository repository;

	@Transactional
	public CurrencyExchange findCurencyExchange(String from, String to) {
		CurrencyExchange rate = repository.findByFromAndTo(from, to);
		return rate;
	}

}
