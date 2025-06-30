package parseJSON;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	public static void main(String[] args)
	{
		JsonPath jsPath = new JsonPath(Payload.CoursePrice());
		int totalCourses = jsPath.getInt("courses.size()");
		System.out.println(totalCourses);
		
		
		
	}

}
