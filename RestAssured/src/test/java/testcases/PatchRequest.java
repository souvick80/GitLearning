package testcases;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class PatchRequest {

	@Test
	public void patchRequestTest() {

		baseURI = "https://reqres.in/api";

		JSONObject enterData = new JSONObject();
		enterData.put("name", "john");
		enterData.put("job", "BA");

//		System.out.println(enterData.toJSONString());

		given()
			.body(enterData.toJSONString())
		.when()
			.patch("/users/696")
		.then()
			.statusCode(200)
			.log().body();
	}
}
