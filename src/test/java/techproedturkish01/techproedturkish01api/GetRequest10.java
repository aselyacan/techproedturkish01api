package techproedturkish01.techproedturkish01api;
import static io.restassured.RestAssured.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GetRequest10 extends TestBase{
	/*
	 * When I send GET Request to URL
	 http://dummy.restapiexample.com/api/v1/employees
	 Then
	  Status code is 200
	  1)10'dan buyuk tum id’leri console’a yazdir
	  10'dan buyuk 14 tane id oldugunu verify et
	  2)30'dan kucuk tum yaslari console’a yazdir
	  30 dan kucuk en buyuk yasin 23 oldugunu verify et
	  3)Maasi 350000'den cok olan iscilerin isimlerini console’a yazdir
	  Charde Marshall’in maasinin 350000'den buyuk oldugunu verify et
	 */
	@Test
	public void get01() {
		Response response=given().
				                 spec(spec02).
				          when().
				                get();
		
		response.prettyPrint();
	
		response.
		        then().
		        assertThat().
		        statusCode(200);
		JsonPath json=response.jsonPath();// bu formatin icinde rahat gezmek icin olusuturuyoruz 
		SoftAssert softAssert=new SoftAssert();
		
		// 10 dan buyuk id`leri consoele`a yazdir. groovy format  interview sorusu ve syntax unutma 
		List<String> idList=json.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
		System.out.println(idList);
		
		//30`dan kucuk tum yaslari console yazdir
		List<String> yasList=json.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
		System.out.println(yasList);
		// 30 dan kucuk en buyuk  yasin 23  oldugunu verif et 
		Collections.sort(yasList);//[19,21,22,23,23]
		softAssert.assertTrue(yasList.get(yasList.size()-1).equals("23"),"Yas istenen gibi degil");
		//string lerin esitiligini equal ile bakiyoruz ...inter olsaydi for loop ile esitlik bakilirdi..
		//verify 
		softAssert.assertEquals(idList.size(), 14,"eleman sayisi  istenen gibi degil ");
		
		softAssert.assertAll();
		
		/*  ODEV 
		 * 3)Maasi 350000'den cok olan iscilerin isimlerini console’a yazdir
	  Charde Marshall’in maasinin 350000'den buyuk oldugunu verify et
		 */
		
	}

}
