package techproedturkish01.techproedturkish01api;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import techproedturkish01.techproedturkish01api.TestBase;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GetRequest12 extends TestBase {
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
									get();
		response.prettyPrint();
		
		List<HashMap<String,String>> listOfMaps=response.as(ArrayList.class);
		System.out.println(listOfMaps);
		System.out.println(listOfMaps.get(1));
		SoftAssert softAssert = new SoftAssert();
		
		// 200 tane id oludunu  verify ediniz
		softAssert.assertEquals(listOfMaps.size(), 200);
		
		
		//121 . elemanin completed  degerini  true  oldunu verfy edniz
		softAssert.assertEquals(listOfMaps.get(120).get("completed"),true,"istenen gibi degil");
		                       //120 . eleman getiri ve onun completed onu verir , true dogrulamasi olur  
		//sondan bir onceki elemanin title nin 
		
		softAssert.assertEquals(listOfMaps.get(listOfMaps.size()-2).get("title"), "numquam repellendus a magnam");
		                      // butun listi   // sondan ikinci eleman //title   
		softAssert.assertAll();
		
		
		
		
		
		
		
		
		
		
		
		
	}
}