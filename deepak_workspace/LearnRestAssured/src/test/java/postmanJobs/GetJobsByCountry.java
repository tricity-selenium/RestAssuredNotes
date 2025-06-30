package postmanJobs;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class GetJobsByCountry {

	public static void main(String[] args) {
		// given - All Input Details
		// When - Submit the API, Resource and Method
		// then - validate the response	
		
		RestAssured.baseURI = "https://jobs.postmanatwork.com/";
		given().queryParam("country", "US")
		.when().get("/jobs")
		.then().log().all().assertThat().statusCode(200);
		
		

	}

}
