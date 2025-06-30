package practice;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GitHubCreateRepo {
  @Test
  public void crateRepo() {
	  
	  String token = "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";
	  
	  RestAssured.baseURI = "https://api.github.com";
	  
	  String requestBody ="{\n"
	  		+ "          \"name\": \"restassured-repo\",\n"
	  		+ "          \"description\": \"Created via RestAssured\",\n"
	  		+ "          \"private\": false\n"
	  		+ "        }";
	  
	  
	  Response response = RestAssured.given()
			              .header("Authorization" , "Bearer " + token)
			              .contentType(ContentType.JSON)
			              .accept(ContentType.JSON)
			              .body(requestBody)
			              .when().post("/user/repos")
			              .then()
			              .statusCode(201)
			              .extract()
			              .response();
	  
	  System.out.println("Response is as below \n " + response.prettyPrint());
			              
  }
}
