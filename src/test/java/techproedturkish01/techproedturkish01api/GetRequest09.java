package techproedturkish01.techproedturkish01api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetRequest09 extends TestBase{
	
	@Test
	public void get01() {
		Response response=given().
				                 spec(spec02).
				          when().
				                 get();
		response.prettyPrint();
		
		//JsonPath objesi olusuturalim
		JsonPath json=response.jsonPath();
		//tum employee isimlerini  console yazdiriniz
		System.out.println(json.getList("data.employee_name")); //data classinin  employe_name
		//ikinci iscinin  isminin  Garret Winters oldugunu "verify" ediniz
		
		// bu hart Assertion dur ve hata oldugu zaman test durur.. 
		//soruda "verify "diyor onun icin soft assertion kullanilmali
		assertEquals("isim istenen gibi degil ","Garrett Winters",json.getString("data[1].employee_name"));
		// data bir list dir ve  aralarinda virgul index numaraasini gosterir ve istenen elemana  koseli
		//parantez ile ulasiriz  json.getString("data[1].employee_name")
		
        /*Soft Assertion icin 3 adim takip  edilmelidir 
         *            1 SoftAssert classin dan bir obje  olustururuz (softassert)
         *            2 objeyi (softassert) kullanarak  asseryion yap
         *            3  en mutlaka  softAssert.assertAll(); olmali aksi durumda  
         *            test hatali sonuc verir ve hepsi pass gibi gorunur
         * 
         */
		SoftAssert softAssert= new SoftAssert();
		softAssert.assertEquals(json.getString("data[1].employee_name"),"Garrett Winters", "isim istenen gibi degil");
		softAssert.assertAll();
		//testNg de message en son da olmali 
		//junit de ise messaj en bas olmali 
		//burasi iyi bir interview sorusu dur 
		
		// iscilerin arasinda  "Herrod Chandler" var oldugunu verify ediniz
		
		// isimler bir list in icinde oldugu icin btun isimleri listen aldik ve icerir mi anlaminda contains ile check ettik
		softAssert.assertTrue(json.getList("data.employee_name").contains("Herrod Chandler"),"Herrod Chandler yok");
		
		
		//24  tane isci oldugunu verif ediniz
		softAssert.assertEquals(json.getList("data.id").size(),24, "24 isci yok ");
		
		//7.iscinin  maasininin 137500 oldugunu verify ediniz
		softAssert.assertEquals(json.getString("data[6].employee_name"),"137500", "Maas istenen gibi degil");
		softAssert.assertAll();
		
	}

}
