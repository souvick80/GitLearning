package testcases;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class DeleteRequest {
	
	@Test
	public void deleteRequestTest() {

		baseURI = "https://reqres.in/api";
		
		when()
			.delete("users/451")
		.then()
			.statusCode(204)
			.log().body();
		
		
	}
	

}
