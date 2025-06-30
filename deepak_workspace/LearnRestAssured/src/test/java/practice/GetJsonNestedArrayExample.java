package practice;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetJsonNestedArrayExample {

	@Test
	public void validateJsonArrayResponse()
	{
		
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		
		Response response = RestAssured
				            .given()
				            .when().get("/users")
				            .then()
				            .statusCode(200)
				            .log().status()
				            .extract()
				            .response();
		
		JsonPath jsonpath = response.jsonPath();
		
		List<String> allCities = jsonpath.getList("address.geo.lng");
		List<String> allCompanies = jsonpath.getList("company.name");
		
		System.out.println("Total Cities: " + allCities.size());
		
		for(int i=0; i<allCities.size(); i++)
		{
			System.out.println(allCities.get(i));
		}
		
		System.out.println("Total Companies: " + allCompanies.size());
		
		for(String company : allCompanies)
		{
			System.out.println(company);
		}
			
	}
	
}
