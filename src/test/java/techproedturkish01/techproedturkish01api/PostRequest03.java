package techproedturkish01.techproedturkish01api;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;
         /*
          * * POST Scenario:
					Accept type Json olsun(Content Type demektir)
					When 
					POST request yolladigimda
					1) https://restful-booker.herokuapp.com/booking
					2) Request Body 
					{
					"firstname": "Suleyman",
					"lastname": "Alptekin",
					"totalprice": 123,
					"depositpaid": true,
					"bookingdates": {//json format bu 
					
					                "checkin": "2020-05-02",
					                "checkout": "2020-05-05"
					},
					"additionalneeds": "Wifi"
					}
					Then 
					Status Code 200 olmali
					Response Body'nin, Request Body ile ayni oldugunu verify ediniz.
          */


  


              public class PostRequest03  extends TestBase{
	
	@Test
	public void post01() {
		Response response=creatRequestBodyByMap();// tESTBase de  method var ona bak .. burda create edildi 
		
		response.prettyPrint();
		
		//Status Code 200 olmali
		response.
		     then().
		     assertThat().
		     statusCode(200);
		
		//JsonPath kullanarak assertion yapalim
		JsonPath json = response.jsonPath();
		SoftAssert softAssert = new SoftAssert();
		
		//firstname assertion
		softAssert.assertEquals(json.getString("booking.firstname"), "asilcan");
		
		//lastname assertion
		softAssert.assertEquals(json.getString("booking.lastname"), "Ruhumun Gidasi");
		
		//totalprice assertion
		softAssert.assertEquals(json.getInt("booking.totalprice"), 123);
		
		//depositpaid assertion
		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), true);
		
		//checkin assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02");
		
		//checkout assertion
		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05");
		
		//additionalneeds assertion
		softAssert.assertEquals(json.getString("booking.additionalneeds"), "Pazar Kahvaltisi");
		
		softAssert.assertAll();
		
		
		
	}
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  
            	  

    }
