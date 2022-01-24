package testcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PutRequest {
	
	
	@Test
	public void putRequestTest() {
		
		baseURI = "https://reqres.in/api";
		
		JSONObject enterData = new JSONObject();
		enterData.put("name", "john");
		enterData.put("job", "dev");
		
//		System.out.println(enterData.toJSONString());
		
		given()
			.header("Content-Type", "application/json")
			.header("Connection", "keep-alive")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(enterData.toJSONString())
		.when()
			.put("/users/696")
		.then()
			.statusCode(200)
			.log().all();
		

		
	}

}
