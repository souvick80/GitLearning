package testcases;

import static io.restassured.RestAssured.*;


import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class ExtrectValuesFromBody {
	
	
	@Test
	public void getValue() {

		baseURI = "https://reqres.in/api";
		
		JSONObject postData = new JSONObject();
		postData.put("email", "eve.holt@reqres.in");
		postData.put("password", "pistol");
		
		
		String token =	given()
							.header("Content-Type", "application/json")
							.accept(ContentType.JSON)
							.body(postData.toJSONString())
						.when()
							.post("/login")
						.then()
							.extract().path("token");
		
		System.out.println(token);
		
	}

}
