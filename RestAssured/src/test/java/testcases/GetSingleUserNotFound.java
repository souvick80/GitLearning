package testcases;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;


public class GetSingleUserNotFound {
	
	@Test
	public void validateGetSingleUserNotFound() {

		baseURI = "https://reqres.in/api";
		
		given()
			.get("/users/23")
		.then()
			.statusCode(404)
			.log().status();
		
	}

}
