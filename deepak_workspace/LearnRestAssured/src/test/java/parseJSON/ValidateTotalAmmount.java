package parseJSON;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;

public class ValidateTotalAmmount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jsPath = new JsonPath(Payload.CoursePrice());
		int totalCourses = jsPath.get("courses.size()");
		int totalOfAllCourses = 0;
		System.out.println(totalCourses);
		for(int i=0; i<totalCourses; i++)
		{
			int copiesSold = jsPath.get("courses["+i+"].copies");
			int unitPrice = jsPath.get("courses["+i+"].price");
			int totalOfOneCourse = copiesSold*unitPrice;			
			totalOfAllCourses = totalOfAllCourses +totalOfOneCourse;
		}
		
		System.out.println(totalOfAllCourses);
		
	        int purchaseAmmount = jsPath.getInt("dashboard.purchaseAmount");
	        
	        System.out.println(purchaseAmmount);
	        
	        Assert.assertEquals(totalOfAllCourses, purchaseAmmount);
		
		

	}

}
