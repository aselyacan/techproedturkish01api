package techproedturkish01.techproedturkish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PostRequest01 extends TestBase{

	/*
	 * PostRequest olusturmak icin gerekenler :
	 *          
	 *          1-EndPoint sart
	 *          2-Request body sart
	 *          3-Authorization sart
	 *          4-Accept Type istege baglidir bazen kullanilir bazen kallanilmaz
	 *          5-ContentType istege baglidir bazen kullanilir bazen kullanilmaz
	 *          
	 *          
	 *   Get ile Post requestlerin farklari nedir ?
	 *           
	 *           1-Get biz  sql de ki select gibi  data cekmek icin kullaniriz 
	 *           2- Get Request olusturmak icin sadece Endpoint yeterliri 
	 *           ama Post Request olusturmaka icin Endpoin yaninda  Request body de gerekir 
	 *           3-Get data cekmek icin ,Post yeni data olusturmak icin kullanilir 
	 *           
	 *           Note: API Developer ler  olurulacak  data nin bazi  bolumlerinin bos birakilmasina karar  vermislerse 
	 *           
	 *              Post Request olusturuken kesinlikle  o kisimlara  deger verilerek  Post Request olusturulmalidir ..
	 *              eger buna dikkat etmezseniz  400 Bad Request STATUS CODE alirsiniz ve bu sizin hataniz yoneticiye demeyin
	 *              
	 *           Note: API Developer lar olusturulacak data nin bazi bolumlerinin tekrarli olmamasina karar vermislerse 
	 *            POST REQUEST olurusturken o kisimlara  tekrarli degerler verilerek Post Reques olusturulmalidir.Eger buna dikkat 
	 *            etmezseniz 400 Bad Request status code alirisiniz
	 *            
	 *            Eger data yeni Data olusturulmasina izin verilmiyorsa  o zaman var olan bir data yi sil sonra onu tekrar creat et 
	 *            ve Post Request test etmis olursun
	 *                            
	 *                  	
	 */
	/*
	 *            POST Scenario:
                  Accept type Json olsun // contentType olacak on kosul veriTipi  postMethodlarda 
                  When 
                  POST request yolladigimda
                   
               1) https://restful-booker.herokuapp.com/booking
               2) Request Body 
                {
                "firstname": "Suleyman",
                "lastname": "Alptekin",
                "totalprice": 123,
                "depositpaid": true,
                "bookingdates": {
                "checkin": "2020-05-02",
                "checkout": "2020-05-05"
                 },
               "additionalneeds": "Wifi"
                }
               Then 
               Status Code 200 olmali
                Response Body, Request Body ile ayni olmali.
	 */
	
	
	//Post da Tam data gondermemiz gerekiyor yoksa Bad Request aliriz
	@Test
	public void post01() {
	String jsonRequestBody = "{\n" + 
			                  "\"firstname\": \"asilcan\",\n" + 
			                  "\"lastname\": \"Ruhumun Gidasi\",\n" + 
			                  "\"totalprice\": 123,\n" + 
			                  "\"depositpaid\": true,\n" + 
			                  "\"bookingdates\": {\n" + 
			                  "\"checkin\": \"2020-05-02\",\n" + 
			                  "\"checkout\": \"2020-05-05\"\n" + 
			                  "},\n" + 
			                  "\"additionalneeds\": \"Pazar Kahvaltisi\"\n" + 
			                  "}";
	Response response=given().
			                 contentType(ContentType.JSON).// icerik tipi demek ve onkosul gibi sartlarda varsa  given den sonra yaz
			                 //icerik tipi ,database e yolladigim data nin icerik tipi JSON 
			                 //post da given dan sonra ContentType (ContentType.JSON) kullan
		                     spec(spec01).//url testbase de ki endPoind spec01 ile ayni ......farz
			                 auth().//authorisation demek postmanda ki autorisation anlaminda
			                 basic("admin", "password123").//basic autoratisin ve username ve password 
			                 body(jsonRequestBody). //farz
			                 when().
			                 post("booking");
	
	response.prettyPrint();
	
	
	//Status code 200 olmali
	response.
	        then().
	        assertThat().
	        statusCode(200);
	         
	
	
	       //JsonPath kullanarak assertion yapalim
	
	JsonPath json=response.jsonPath();//jsonPath classindan bir obje olusturduk
	SoftAssert so=new SoftAssert();
	//firstName assertion 
	so.assertEquals(json.getString("booking.firstname"), "asilcan");
	//lastname assertion
	so.assertEquals(json.getString("booking.lastname"), "Ruhumun Gidasi");
	
	//total Price
	so.assertEquals(json.getInt("booking.totalprice"), 123);
	
	// depostitpaid
	so.assertEquals(json.getBoolean("booking.depositpaid"), true);
	
	//checkIn assertion
	so.assertEquals(json.getString("booking.bookingdates.checkin"), "2020-05-02");
	//checkout assertion
	so.assertEquals(json.getString("booking.bookingdates.checkout"), "2020-05-05");
	
	//addtionalneeds assertion
	so.assertEquals(json.getString("booking.additionalneeds"), "Pazar Kahvaltisi");
	so.assertAll();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
