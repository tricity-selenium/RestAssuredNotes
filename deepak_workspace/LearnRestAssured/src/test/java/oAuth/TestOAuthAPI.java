package oAuth;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.API;
import pojo.GetCourse;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

public class TestOAuthAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String response =

                given() .formParams("client_id", "abcd.apps.googleusercontent.com")

                        .formParams("client_secret", "1234567890")

                        .formParams("grant_type", "client_credentials")

                        .formParams("scope", "trust")
                       
                        .when().log().all()
                        .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();

//System.out.println(response);

JsonPath jsonPath = new JsonPath(response);

    String accessToken = jsonPath.getString("access_token");

    System.out.println(accessToken);

    GetCourse getCourse = given().queryParams("access_token", accessToken)
               .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
               .as(GetCourse.class);

    System.out.println(getCourse.getInstructor());
    System.out.println(getCourse.getExpertise());
    System.out.println(getCourse.getLinkedIn());
    System.out.println(getCourse.getServices());
    System.out.println(getCourse.getUrl());
    System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());
    System.out.println(getCourse.getCourses().getApi().get(1).getPrice());
    List<API> apiList = getCourse.getCourses().getApi();
    for(API ap : apiList)
    {
    	if(ap.getCourseTitle().contains("Soap"))
    	{
    		
    		System.out.println(ap.getPrice());
    	}
    }
   List<WebAutomation>  webList =  getCourse.getCourses().getWebAutomation();
   for(WebAutomation web : webList)
   {
	   System.out.println("Courses Available in Web is = " + web.getCourseTitle());
	   
   }
    
    
    
	}

}
