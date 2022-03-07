package com.coderhop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhop.dao.StoreData;
import com.coderhop.dao.TestDAO;

import reactor.core.publisher.Flux;

@RestController
public class TestController {
	@Autowired
	TestDAO testdao;

	@GetMapping("/execute")
	public Flux<StoreData> getPostGisResponse() {
		return testdao.execute();
	}

}
