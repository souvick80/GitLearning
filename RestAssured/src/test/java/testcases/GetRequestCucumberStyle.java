package testcases;

import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class GetRequestCucumberStyle {


	@Test
	public void getUserListTest() {

		// Base Url: to use it import must be static
		// Ex: import static io.restassured.RestAssured.baseURI;
		baseURI = "https://reqres.in/api";

		//		Response res = get( "https://reqres.in/api/users?page=2");

		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			.log().ifValidationFails();

	}

	// To validate the existence of certain value in specified location in the body
	@Test
	public void validateGetUserListData() {

		baseURI = "https://reqres.in/";

		given()
			.get("/users?page=2")
		.then()
			.statusCode(200)
			
			.body("data[0].id", equalTo(7))
			.body("data[1].first_name", equalTo("Lindsay"))
			
			.log().ifValidationFails();

	}
	
	// to validate certain items are present in the body or not
	@Test
	public void validateGetUserExistance() {
		
		baseURI = "https://reqres.in/";

		given()
			.get("/users?page=2")
		.then()
			.statusCode(20)
			
			.body("data.first_name", hasItem("Michael"))
			.body("data.first_name", hasItems("Lindsay", "Tobias"))
			
			.log().ifValidationFails(LogDetail.STATUS);
		
	}
}
