package techproedturkish01.techproedturkish01api;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


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

//POJO:Plain Old Java Object ===Json formatini  classa  cevirmek icin kullanilir ve  Interview sorusu dur 

        public class PostRequest04 extends TestBase {
	
	
        @Test
        	public void post01() {
        		
        BookingDates bookingDates=new BookingDates("2020-05-02", "2020-05-05"); //ctrl +space ile ikili BookingsDates secenegi cikar ve onu sec yukaridan al ,
        Booking      booking=new Booking("Suleyman", "Alptekin", 123, true, bookingDates, "Wifi");//Booking isimini  yazdiktan hemen sonra ctrl+space yap ve yukaridan doldur
        
        Response response=given().
        		                 contentType(ContentType.JSON).
        		                 spec(spec01).
        		                 auth().
        		                 basic("admin","password123").
        		                 body(booking).//toString medotu icinde oldugu icin body hata vermez halbuki body mutlaka string olmali toString meth da booking class.da
        		            when().
        		                 post("/booking");
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
      		softAssert.assertEquals(json.getString("booking.firstname"), "Suleyman");
      		
      		//lastname assertion
      		softAssert.assertEquals(json.getString("booking.lastname"), "Alptekin");
      		
      		//totalprice assertion
      		softAssert.assertEquals(json.getInt("booking.totalprice"), 123);
      		
      		//depositpaid assertion
      		softAssert.assertEquals(json.getBoolean("booking.depositpaid"), true);
      		
      		//checkin assertion
      		softAssert.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02");
      		
      		//checkout assertion
      		softAssert.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05");
      		
      		//additionalneeds assertion
      		softAssert.assertEquals(json.getString("booking.additionalneeds"), "Wifi");
      		
      		softAssert.assertAll();
      		
        		
        	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
