package com.base;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class Base {
	
	@BeforeTest
	public void init() {
		
		RestAssured.baseURI = "https://qa-task.backbasecloud.com";
	}

}
