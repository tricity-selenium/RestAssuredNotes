package eCommerce;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class EComAPITest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	RequestSpecification req = new RequestSpecBuilder()
			                   .setBaseUri("https://rahulshettyacademy.com")
			                   .setContentType(ContentType.JSON)
			                   .build();
	
	POJO_LoginRequest loginRequest = new POJO_LoginRequest();
	loginRequest.setUserEmail("deepaksharma@mailinator.com");
	loginRequest.setUserPassword("MyPassword123#");
	
	RequestSpecification reqLogin = given().spec(req).body(loginRequest);
	
	POJO_LoginResponse loginResponse = reqLogin.when().post("api/ecom/auth/login")
			                           .then().extract().response().as(POJO_LoginResponse.class);
	
	System.out.println(loginResponse.getToken());
	String authToken = loginResponse.getToken();
	System.out.println(loginResponse.getUserId());
	String userId = loginResponse.getUserId();
	
	//Add Product
	
	RequestSpecification addProductBaseReq = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com")
            .addHeader("Authorization", authToken)
            .build();
	
	RequestSpecification addProduct = given().spec(addProductBaseReq)
										.param("productName", "Laptop")
										.param("productAddedBy", userId)
										.param("productCategory", "Electronics")
										.param("productSubCategory", "Computers")
										.param("productPrice", 10000)
										.param("productDescription", "Best Quality Product")
										.param("productFor", "EveryOne")
										.multiPart("productImage", new File("C:\\Users\\HP\\Downloads\\pngegg.png"));
	
	String addProductResponse = addProduct.when().post("/api/ecom/product/add-product")
	.then().extract().response().asString();
	
	JsonPath js = new JsonPath(addProductResponse);
	String productId = js.get("productId");
	System.out.println(productId);
	
	// Create Order
	RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com")
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", authToken)
            .build();
	
	POJO_OrderDetails orderDetails = new POJO_OrderDetails();
	orderDetails.setCountry("India");
	orderDetails.setProductOrderedId(productId);
	
	List<POJO_OrderDetails> orderDetailsList = new ArrayList<POJO_OrderDetails>();
	orderDetailsList.add(orderDetails);
	
	POJO_Orders order = new POJO_Orders();
	order.setOrders(orderDetailsList);
	
	RequestSpecification createOrder = given().log().all().spec(createOrderBaseReq).body(order);
	String responseOrder = createOrder.when().post("/api/ecom/order/create-order")
	.then().log().all().extract().response().asString();
	
	
	//Delete Product
	
	RequestSpecification deleteProductBaseReq = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com")
            .setContentType(ContentType.JSON)
            .addHeader("Authorization", authToken)
            .build();
	
	RequestSpecification deleteProduct = given().log().all().spec(deleteProductBaseReq)
			                                     .pathParam("productId", productId);
	
	String deleteProductResponse = deleteProduct.when().delete("/api/ecom/product/delete-product/{productId}")
	.then().log().all().extract().response().asString();
	
	JsonPath jsDeletePro = new JsonPath(deleteProductResponse);
	System.out.println(jsDeletePro.get("message"));
	
	
	
	
	

	}

}
