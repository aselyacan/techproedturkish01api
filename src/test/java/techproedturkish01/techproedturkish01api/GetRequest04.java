package techproedturkish01.techproedturkish01api;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
public class GetRequest04 {
	
	/*
	 Positive Scenario:
	 When I send a GET request to REST API URL
	 http://dummy.restapiexample.com/api/v1/employees
     And Accept type is “application/JSON”
     Then
     HTTP Status Code should be 200
     And Response format should be “application/JSON”
     And there should be 24 employees
     And “Ashton Cox” should be one of the employees
     And 21, 61, and 23 should be among the employee ages
	 */

	@Test
	public void get01() {
		Response response=given().//ContentType.JSON accept icine yazilir cunku string gorulmsi hos olmaz
				                  accept(ContentType.JSON).// “application/JSON”
				          when().get("http://dummy.restapiexample.com/api/v1/employees"); 
		
		response.prettyPrint();
		
		response.
		         then().
		         assertThat().
		         statusCode(200).
		         contentType(ContentType.JSON).// “application/JSON” da kullanilir ama bu daha iyi
		         body("data.id",Matchers.hasSize(24)).// id leri karsilastirma icin Matchers.hasSize meth
		         body("data.employee_name",Matchers.hasItem("Ashton Cox")).
		         body("data.employee_age",Matchers.hasItems("21","61","23"));// birden fazla veri girilebilir
					          
	}
	
	
}
