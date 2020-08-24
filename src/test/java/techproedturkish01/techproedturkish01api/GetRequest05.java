package techproedturkish01.techproedturkish01api;
import org.hamcrest.Matchers;
import org.junit.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest05 {
	/*
	 * When I send a GET request to REST API URL 
		   https://restful-booker.herokuapp.com/booking/5  
	   Then HTTP Status Code 200 olsun
	   And Response content type “application/JSON” olsun
	   And “firstname” “Jim” olsun
	   And “totalprice” 602 olsun
		 And “checkin” “2015-06-12" olsun
	 */
	@Test
	public void test01() {
		
		Response response=given().
				          when().
				          get(" https://restful-booker.herokuapp.com/booking/5  ");
		
		response.prettyPrint();
		
		response.
		         then().
		         assertThat().
		         statusCode(200).
		         contentType(ContentType.JSON).
		         body("data.firstname",Matchers.hasItem("Mark"),
		               "totalprice",Matchers.equalTo(892),
		               "checkin",Matchers.equalTo("2015-06-12"));
		         
	}

}
