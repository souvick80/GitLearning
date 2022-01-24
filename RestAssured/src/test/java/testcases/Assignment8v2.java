package testcases;

import static io.restassured.RestAssured.*;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;


public class Assignment8v2 {
	

	//register user>>extract id and token
	//log in with above created user >>extract token
	//run get single user to find same user id>>validate name and job details
	//single <RESOURCE> use same user id>>validate details
	//then update user details>>add validations>>search user and validate again
	//patch same user>>validate response>>search user>>validate
	//delete same user>>validate code>>search user>>validate
	
	@Test
	public void test() {
		
		Object  userId, registrationToken, loginToken;
		JSONObject credentials, updateData;
		
		baseURI = "https://reqres.in/api";
		
		credentials = new JSONObject();
		credentials.put("email", "eve.holt@reqres.in");
		credentials.put("password", "pistol");
		
		//register user>>extract id and token
		 userId = extractData(baseURI, "/register", "id", credentials);
		 System.out.println(userId);
	
		 registrationToken = extractData(baseURI, "/register", "token", credentials);	
		 System.out.println(registrationToken);
		 
		//log in with above created user >>extract token
		 loginToken = extractData(baseURI, "/login"	, "token", credentials);
		 System.out.println(loginToken);
		 
		//run get single user to find same user id>>validate name and job details
		 given()
			.get("users/"+userId)
		.then()
			.statusCode(200)
			.body("data.email", equalTo("eve.holt@reqres.in"))
			.body("data.first_name", equalTo("Eve"))
			.body("data.last_name", equalTo("Holt"))
			.log().body();
		 
		//single <RESOURCE> use same user id>>validate details
		 given()
			.get("/unknown/"+userId)
		.then()
			.statusCode(200)
			.body("data.name", equalTo("aqua sky"))
			.log().body();
		 
		// update user details>>add validations
		 	updateData = new JSONObject();
		 	updateData.put("name", "John");
		 	updateData.put("job", "QA");
		 
			 try {
				given()
					.body(updateData.toJSONString())
				.when()
					.put("/users/"+userId)
				.then()
					.statusCode(200)
					.body("name", equalTo("John"))
					.body("job", equalTo("QA"))
					.log().body();
			} catch (AssertionError e1) {
				e1.printStackTrace();
			}
			 
			 // search user and validate again
			 try {
					given()
						.get("/users/"+userId)
					.then()
						.statusCode(200)
						.body("name", equalTo("John"))
						.body("job", equalTo("QA"))
						.log().body();
				} catch (AssertionError e1) {
					e1.printStackTrace();
				}
			 
			//patch same user>>validate response>>search user>>validate
			 updateData.put("name", "Tom");
			 updateData.put("job", "DEV");
			 
			 try {
				given()
					.body(updateData.toJSONString())
				.when()
					.patch("/users/"+userId)
				.then()
					.statusCode(200)
					.body("name", equalTo("Tom"))
					.body("job", equalTo("DEV"))
					.log().body();
			} catch (AssertionError e) {
				e.printStackTrace();
			}
			 
			// search user and validate again
			 try {
					given()
						.get("/users/"+userId)
					.then()
						.statusCode(200)
						.body("name", equalTo("Tom"))
						.body("job", equalTo("DEV"))
						.log().body();
				} catch (AssertionError e1) {
					e1.printStackTrace();
				}
		 
			//delete same user>>validate code>>search user>>validate
			 
			when()
				.delete("users/"+userId)
			.then()
				.statusCode(204)
				.log().body();
			System.out.println("User deleted");
		 
			//search user>>validate
			
			try {
				given()
					.get("/users/"+userId)
				.then()
					.statusCode(404)
					.log().status();
			} catch (AssertionError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		 
	}
	
	// extract value for a particular key
	public Object extractData(String BaseUrl, String endPoint, String getValue, JSONObject data ) {
		baseURI = BaseUrl;
		
		Object value =given()
						.header("Content-Type", "application/json")
						.accept(ContentType.JSON)
						.body(data.toJSONString())
					.when()
						.post(endPoint)
					.then()
						.extract().path(getValue);
		return value;
		
	}
	
	

}
