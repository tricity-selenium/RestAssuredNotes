package postmanJobs;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class VerifyResponseBody {

	public static void main(String[] args) {
		// given - All Input Details
		// When - Submit the API, Resource and Method
		// then - validate the response	
		RestAssured.baseURI = "https://jobs.postmanatwork.com/";
		
		given()
		.when().get("/jobs")
		.then().log().all()
		.assertThat().statusCode(200).header("Server", ("nginx/1.20.0"));
		
		
		

	}

}
