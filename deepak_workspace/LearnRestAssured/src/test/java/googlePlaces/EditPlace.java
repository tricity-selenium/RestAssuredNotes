package googlePlaces;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class EditPlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", "899276cfb32ceb53153ccc93b0616d00")
		.body("{\r\n"
				+ "\"place_id\":\"899276cfb32ceb53153ccc93b0616d00\",\r\n"
				+ "\"address\":\"555 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200);

	}

}
