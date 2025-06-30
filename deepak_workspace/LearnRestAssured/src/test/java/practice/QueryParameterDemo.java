package practice;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class QueryParameterDemo {
  @Test
  public void queryparams() {
	  
	  RestAssured.baseURI = "https://jobs.postmanatwork.com/";
	  
	  Response response = RestAssured.given().queryParam("country", "US")
			              .when().get("/jobs")
			              .then()
			              .log().all()
			              .statusCode(200)
			              .extract()
			              .response();
	  
	  JsonPath jpath = response.jsonPath();
	  List<String> countrylist = jpath.getList("country");
	  
	  for(String country  : countrylist)
	  {
		  System.out.println("checking country");
		  Assert.assertEquals(country, "US", "Country is mismatched");
		 
	  }
	  			  
  }
}
