package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.*;
import java.util.HashMap;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
public class GetRequest11 extends TestBase{
	/*
	 * GSON : GSON , 1) Json formatindaki data’laari Java Objectlerine donusturur (De - Serialization)
	 * 				 2) Java Object’lerini Json formatindaki data’lara donusturur. (Serialization)
	 * 
	 * NOTE : GSON ile ayni isi yapan ObjectMapper class’da var
	 * 
	 */
	
	@Test
	public void get01() {
		Response response = given().
								spec(spec03).
							when().
								get("/2");
		response.prettyPrint();
		
		HashMap<String,String> map = response.as(HashMap.class); // De-Serialization 
		System.out.println(map);
		
		System.out.println(map.keySet());//[id,completed,title,userId]
		System.out.println(map.values());//[2.0,false,quis ut nam facilis et officia qui,1.0]
		
		SoftAssert softAssert = new SoftAssert();
		//‘completed’ key’sinin degerinin false oldugunu ‘verify’ ediniz 
		
		
		 softAssert.assertEquals(map.get("completed"),false,"False olmaliydi ama degil");
		
		 
		 //userId, id ve title degerlerini degerlerini verify ediniz
		 
		 softAssert.assertEquals(map.get("userId"), 1.0);
		 softAssert.assertEquals(map.get("id"),2.0);
		 softAssert.assertEquals(map.get("title"), "quis ut nam facilis et officia qui");
		
		 softAssert.assertAll();
	}
}