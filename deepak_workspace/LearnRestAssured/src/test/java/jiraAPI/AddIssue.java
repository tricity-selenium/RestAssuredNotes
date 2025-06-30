package jiraAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;
public class AddIssue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://tricityselenium-1726504058697.atlassian.net";
		
		String addIssueResponse  = given().header("Content-Type", "application/json")
		.header("Authorization", "Basic dHJpY2l0eS5zZWxlbml1bUBnbWFpbC5jb206QVRBVFQzeEZmR0YwNnZPUzdzWDBHejN1QmhMTnE4TUhFOGVPanJYSTRNS3BLRDlSeVlkXzlLUlAzNlh6Wll6NkxiSmM0dm9LVGd1aEVGcWZHR0JwbUluZmdYLTJRd0h2UG1faWZwa0g1RVg4UkFxNmhqaDR6dlk1M1ZpSTNJYlpMX0I5dVBsWllNRldrYmZ6SjVGRVNXNV95LUlIY25kUjNWZG9VTjJVeExLem1VZlphVVZaMmZrPTRFQzBDODFC")
        .body("{\r\n"
        		+ "    \"fields\": {\r\n"
        		+ "       \"project\":\r\n"
        		+ "       {\r\n"
        		+ "          \"key\": \"SCRUM\"\r\n"
        		+ "       },\r\n"
        		+ "       \"summary\": \"Adding Issue from Rest Assured With Screenshot\",\r\n"
        		+ "        \r\n"
        		+ "       \"issuetype\": {\r\n"
        		+ "          \"name\": \"Bug\"\r\n"
        		+ "       }\r\n"
        		+ "   }\r\n"
        		+ "}")
        .log().all()
        .when().post("/rest/api/3/issue")
        .then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js = new JsonPath(addIssueResponse);
		String bugId = js.getString("id");
		System.out.println(bugId);
		
		given()
		.header("X-Atlassian-Token", "no-check")
		.header("Authorization", "Basic dHJpY2l0eS5zZWxlbml1bUBnbWFpbC5jb206QVRBVFQzeEZmR0YwNnZPUzdzWDBHejN1QmhMTnE4TUhFOGVPanJYSTRNS3BLRDlSeVlkXzlLUlAzNlh6Wll6NkxiSmM0dm9LVGd1aEVGcWZHR0JwbUluZmdYLTJRd0h2UG1faWZwa0g1RVg4UkFxNmhqaDR6dlk1M1ZpSTNJYlpMX0I5dVBsWllNRldrYmZ6SjVGRVNXNV95LUlIY25kUjNWZG9VTjJVeExLem1VZlphVVZaMmZrPTRFQzBDODFC")
		.multiPart("file", new File("C:\\Users\\HP\\OneDrive\\Pictures\\DataDriven.png"))
		.pathParam("bugID", bugId)
		.when().post("/rest/api/3/issue/{bugID}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		
		
	}

}
