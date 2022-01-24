package testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostRegisterSuccessful {
	
	@Test
	public void validatePostRegisterSuccessful() {
		
		baseURI = "https://reqres.in/api";
		
		JSONObject postData = new JSONObject();
		postData.put("email", "eve.holt@reqres.in");
		postData.put("password", "pistol");
		System.out.println(postData.toJSONString());
		
		given()
			.header("Content-Type", "application/json")
			.accept(ContentType.JSON)
			.body(postData.toJSONString())
		.when()
			.post("/register")
		.then()
			.statusCode(200)
			.log().status()
			.log().body();
		
	}

}
