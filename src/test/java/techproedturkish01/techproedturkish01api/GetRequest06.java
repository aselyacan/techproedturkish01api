package techproedturkish01.techproedturkish01api;

import static org.hamcrest.Matchers.*;// coklu matchersin onune gecmek icin butun statikleri al 
import org.junit.Test;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequest06 extends TestBase {
	//TestBase class olusturup her testte  kullanilan data lari TestBase classa koyup 
	//tekrar tekrar  ayni seyleri yazmaktan kurutulacagiz
	
	/*
	 * When I send a GET request to REST API URL
		https://restful-booker.herokuapp.com/booking/5
	    Then HTTP Status Code should be 200
	    And response content type is “application/JSON”
	    And response body should be like;
	    {
		    “firstname”: “Sally”,
		    “lastname”: “Ericsson”,
		    “totalprice”: 111,
		    “depositpaid”: false,
		    “bookingdates”: {
		        “checkin”: “2017-05-23",
		        “checkout”: “2019-07-02"
		     }
	 */
	
	@Test
	public void get01() {
		Response response=given().//spec kullanmak icin extends yapmak zorundayiz 
				               spec(spec01).// main url test base de ilave varsa asagisa eklnier 
				          when().
				               get("/booking/5");
		response.prettyPrint();
		
		response.
		         then().
		         assertThat().
		         statusCode(200).
		         contentType(ContentType.JSON).
		         
		         body("firstname",equalTo("Sally"),
		        	   "lastname",equalTo("Jones"),
		        	   "totalprice",equalTo(249),
		        	   "depositpaid",equalTo(false),
		        	   "bookingdates.checkin",equalTo("2016-04-18"),
		        	   "bookingdates.checkout",equalTo("2019-07-02"),
		        	   "additionalneeds",equalTo("Breakfast"));
		
	}

}
