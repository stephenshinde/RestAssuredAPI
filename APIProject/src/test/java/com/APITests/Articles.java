package com.APITests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Utility.JsonPathUtility;
import com.base.Base;

import io.restassured.path.json.JsonPath;

public class Articles extends Base{
	
	@Test
	public void getListArticles(){
		// given request
		String response =given().auth().preemptive().basic("candidatex", "qa-is-cool").
		header("Content-Type","application/json; charset=utf-8").
		// query parameter with Tag
		queryParam("tag", "Real").
		
		// get operation with resource
		when().get("/api/articles").
		// Assert operation
		then().assertThat().log().all().
		header("Content-Type", "application/json; charset=utf-8").
		statusCode(200).
		extract().response().asString();
		
		JsonPath js= JsonPathUtility.convertjson(response);
		String getTagName = js.getString("articles[0].tagList[0]");
		int articlecount=js.getInt("articlesCount");
		System.out.println("First tag name is : "+getTagName);
		System.out.println("Article count is : "+articlecount);
		// Verify the request returns proper tagname which we applied in query parameter
		Assert.assertEquals(getTagName, "Real");
		
		
		
	}

}
