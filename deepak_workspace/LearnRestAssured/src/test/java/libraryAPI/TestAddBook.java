package libraryAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import parseJSON.Payload;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

public class TestAddBook {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().body(Payload.addBook("isbn8404","PH84" ))
		.when().post("/Library/Addbook.php")
		.then().log().all()
		.statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(response);
	
		
		
	String response2 = given()
		.when().get("/Library/GetBook.php")
		.then().log().all().statusCode(200).extract().response().asString();
		
		

	}

}
