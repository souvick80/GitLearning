package testcases;

import static io.restassured.RestAssured.*;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class PostRequest {
	
	
	
	@Test
    public void postRequestTest() {
		
		baseURI = "https://reqres.in/api";
		
		JSONObject enterData = new JSONObject();
		enterData.put("name", "john");
		enterData.put("job", "qa");
		
//		System.out.println(enterData.toJSONString());
		
		given()
			.header("Content-Type", "application/json")
			.header("Connection", "keep-alive")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(enterData.toJSONString())
		.when()
			.post("/users")
		.then()
			.statusCode(201)
			.log().all();
		
		
		
	}

}
