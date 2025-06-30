package parseJSON;

import io.restassured.path.json.JsonPath;

public class ComplexJSONParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath jsPath = new JsonPath(Payload.CoursePrice());
		int courseCount = jsPath.getInt("courses.size()");
		System.out.println(courseCount);
		
		System.out.println(jsPath.getInt("dashboard.purchaseAmount"));
		

		System.out.println(jsPath.get("courses[0].title"));
		System.out.println(jsPath.get("courses[1].title"));
		System.out.println(jsPath.get("courses[2].title"));
		
		
		for(int i=0; i<courseCount; i++)
		{
			String courseTitle = jsPath.get("courses["+i+"].title");
			   int coursePrice = jsPath.getInt("courses["+i+"].price");
			   System.out.println("CourseTitle: " + courseTitle + " & " + "CoursePrice: " + coursePrice  );
		}
		
		
		for(int i=0; i<courseCount; i++)
		{
			String courseTitle = jsPath.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("cypress"))
			{
			   int copiesSold = jsPath.getInt("courses["+i+"].copies");
			   System.out.println("CourseTitle: " + courseTitle + " & " + "Copies Sold: " + copiesSold  );
			}
		}
		
		
	}

}
