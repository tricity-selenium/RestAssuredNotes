package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestExample {
	
  @Test
  public void validateGetRequest() {
	  
	  RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	  
	  Response response = RestAssured
			              .given()
			              .when().get("/posts/1")
			              .then()
			              .log().all()
			              .extract()
			              .response();
	  
	  Assert.assertEquals(response.getStatusCode(), 200, "Status Code Check Failed");
	  
	  Assert.assertEquals(response.getHeader("Content-Type"), "application/json; charset=utf-8");
	  
	  String title = response.jsonPath().getString("title");
	  Assert.assertNotNull(title, "Title Can not be Null");
	  	  
	  Assert.assertTrue(title.contains("sunt aut facere repellat provident"), "title check failed");
	  
	  
  }
}
