package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequest {

	@Test
	public void getUsersListTest() {

		Response res = RestAssured.get("https://reqres.in/api/users?page=2");

		// Validating the status code
		System.out.println(res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);

		// Getting response body
		System.out.println("The response body: \n"+res.body().asString());

		// Response time
		System.out.println("The response time is: "+ res.getTime());

		// Get header
		System.out.println("content-type: "+res.getHeader("content-type"));

		// Get headers
		System.out.println(res.headers());
	}

	@Test
	public void getSingleUserTest() {
		
		String uId = "2";

		Response res = RestAssured.get("https://reqres.in/api/users/"+uId);

		// Validating the status code
		System.out.println(res.getStatusCode());
		Assert.assertEquals(res.getStatusCode(), 200);

		// Getting response body
		System.out.println("The response body: \n"+res.body().asString());

		// Response time
		System.out.println("The response time is: "+ res.getTime());

		// Get header
		System.out.println("content-type: "+res.getHeader("content-type"));

		// Get headers
		System.out.println(res.headers());
		
		// Get user id
		System.out.println(res.body());

	}
}
