package com.APITests;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;

import com.Utility.JsonPathUtility;
import com.base.Base;

import io.restassured.path.json.JsonPath;

public class Users extends Base{
	@Test
	public void Authentication() {
		//External Payload jsonn file
		File jsonDatafile = new File("src/main/java/com/Resources/Payload.json");
		
		// Given details
		String response =given().auth().preemptive().basic("candidatex", "qa-is-cool").
		header("Content-Type","application/json; charset=utf-8").
		body(jsonDatafile).
		// Post operation 
		when().post("/api/users/login").
		
		//Assert operation
		then().assertThat().log().all().
		//verify header content type
		header("Content-Type", "application/json; charset=utf-8").
		// verify status code
		statusCode(200).
		//Extract response
		extract().response().asString();
		// Parse Json object
		JsonPath js= JsonPathUtility.convertjson(response);
		String token = js.getString("user.token");
		
		System.out.println(token);
		
	}

}
