package techproedturkish01.techproedturkish01api;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;//static olanlari import eder *herseyi al demek

public class GetRequest01 {

	//Rest-Assured kullanarak API Testing yapacagiz	
	
		@Test
		public void getMethod01() {
			
			given().
			when().//den sonra  5 tane post medhodu kullanilir get,put,post,delete gibi..
			   get("https://restful-booker.herokuapp.com/booking").//postman da ki get meth.
			then().//dogrulama yarar ve assert kullanilir..
			   assertThat().
			   statusCode(200);		
		}
		
		//GET ile aldigim data'yi console'da gormek istiyotum
		@Test
		public void getMethod02() {
			
			Response response = given().//burasi bir data ve Response classin nin containere yuklnir
			                    when().
			                    get("https://restful-booker.herokuapp.com/booking/5");
			//Response body'i console'a yazdirmak icin response.prettyPrint(); kullanilir.
			response.prettyPrint();	
			
			//statuscode'u console'da gormek icin;  
			System.out.println("Status code: " + response.getStatusCode());
			
			//statusline'i console'da gormek icin;
			System.out.println("Status line: " + response.getStatusLine());
			
			//Response body'deki data'nin content(icerik) type
			//1. Yol:
			System.out.println("Content Type 1.Yol: " + response.getContentType());
			//2. Yol
			System.out.println("Content Type 2.Yol: " + response.getHeader("Content-Type"));
			
			//Headers'daki tum bilgileri almak icin;
			System.out.println(response.getHeaders());
			
			//Headers'dan istenen specific bir data'yi almak
			System.out.println("GetHeaders den belli bir veriyi almak :"+response.getHeader("Date"));
			
			//Assertion yapalim
			
			//1)Status code 200
			//assertThat() kullanirsaniz "Hard Assertion" yapiyorsunuz demektir.
			//Yani; ilk hatada code execution durur ve hata raporu verilir. 
			//Ilk hatadan sonraki kodlar calistirilmaz.
			response.
			then().//assertThat hard assertion demek ve ilk hata dan sonra execution devam etmez 
			assertThat().// noktadan sonra  assert edecegimiz seyleri pespese  koyabiliriz
			statusCode(200).
			statusLine("HTTP/1.1 200 OK").
			contentType("application/json; charset=utf-8");
		
		}

	
}
