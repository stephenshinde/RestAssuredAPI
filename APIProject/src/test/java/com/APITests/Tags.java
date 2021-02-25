package com.APITests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Utility.JsonPathUtility;
import com.base.Base;

import io.restassured.path.json.JsonPath;


public class Tags extends Base {
	@Test
	public void getTags(){
		//given details
		String response =given().auth().preemptive().basic("candidatex", "qa-is-cool").
	
		// get method with resource
		when().get("/api/tags").
		// Assertion
		then().assertThat().statusCode(200).
		// log response in console
		log().all().
		// verify response content type
		header("Content-Type", "application/json; charset=utf-8").
		//Extract response as string
		extract().response().asString();
		
		JsonPath js= JsonPathUtility.convertjson(response);
		String hashtagName = js.getString("tags[0]");
		// Assert first tag name
		Assert.assertEquals(hashtagName, "microchip");
		// get popular tags count
		int count =js.getInt("tags.size()");
		// print popular tags count
		System.out.println("Count of popular tags are :" +count);
		
		
		
	}

}
