package practice;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetJSONArrayExample {

	@Test
	public void validateJsonArrayResponse()
	{
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
		
		Response response = RestAssured
				            .given()
				            .when().get("/posts")
				            .then()
				            .statusCode(200)
				            .log().status()
				            .extract()
				            .response();
		
		JsonPath jsonpath = response.jsonPath();
		List<Integer> allIds = jsonpath.getList("id");
		List<String> allTitles = jsonpath.getList("title");
		
		Assert.assertEquals(allIds.size(), 100);
		Assert.assertTrue(allTitles.get(0).contains("sunt aut facere"));
		
		System.out.println("Total posts: " + allIds.size());
        System.out.println("First Post ID: " + allIds.get(0));
        System.out.println("First Post Title: " + allTitles.get(0));
		
		
	}
	
	

}
