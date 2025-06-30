package googlePlaces;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class GetPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().queryParam("key", "qaclick123").queryParam("place_id", "899276cfb32ceb53153ccc93b0616d00")
		.when().get("/maps/api/place/get/json")
		.then().log().all().statusCode(200);

	}

}
