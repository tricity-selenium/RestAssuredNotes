package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PathParameterDemo {
  @Test
  public void validateSingleJob() {
	  
	  RestAssured.baseURI = "https://jobs.postmanatwork.com/";
	  
	  Response response = RestAssured.given().pathParam("jobID", "TB18MDx9h5tGkrZMUFycs")
			              .when().get("/jobs/{jobID}")
			              .then().log().all()
			              .statusCode(200)
			              .extract()
			              .response();
	  
	  JsonPath jpath = response.jsonPath();
	  
	  String jobTitle = jpath.get("title");
	  
	  Assert.assertEquals(jobTitle, "Customer Success Enablement Specialist", "Incorrect Job");
	  
	  
  }
}
