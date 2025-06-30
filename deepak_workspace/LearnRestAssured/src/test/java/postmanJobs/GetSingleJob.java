package postmanJobs;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class GetSingleJob {

	public static void main(String[] args) {
		// given - All Input Details
		// When - Submit the API, Resource and Method
		// then - validate the response	
		RestAssured.baseURI = "https://jobs.postmanatwork.com/";
		given().pathParam("jobID", "TB18MDx9h5tGkrZMUFycs")
		.when().get("/jobs/{jobID}")
		.then().log().all().assertThat().statusCode(200).body("title", equalTo("Customer Success Enablement Specialist"));
		
		
		

	}

}
