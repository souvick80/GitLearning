package testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class PostLoginSuccessful {
	
	@Test
	public void validatePostLoginSuccessful() {
		baseURI = "https://reqres.in/api";
		
		JSONObject credentials = new JSONObject();
		credentials.put("email", "eve.holt@reqres.in");
		credentials.put("password", "cityslicka");
		
		System.out.println(credentials.toJSONString());
		
	given()
		.body(credentials.toJSONString())
	.when()
		.post("/login")
	.then()
		.statusCode(200)
		.log().status()
		.log().body();
				
		
	}

}
