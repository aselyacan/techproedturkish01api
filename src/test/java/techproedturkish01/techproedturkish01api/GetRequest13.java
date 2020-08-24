package techproedturkish01.techproedturkish01api;
import static io.restassured.RestAssured.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
public class GetRequest13 extends TestBase{

	
	@Test
	public void get01() {
		Response response=given().
                                spec(spec02).
                          when().
                                get();
		
		response.prettyPrint();
		
		//ilk 5 imsin Tiger Nixon Garret Winters ,Ashton Cox, Cedric Kelly , Airi Satou oldugunu verify ediniz
		
		
		JsonPath json=response.jsonPath();
		SoftAssert softAssert=new SoftAssert();
		
		//1.yol  ama tavisye edilmez 
		softAssert.assertEquals(json.getString("data[0].employee_name"), "Tiger Nixon");
		softAssert.assertEquals(json.getString("data[1].employee_name"), "Garrett Winters");
		softAssert.assertEquals(json.getString("data[2].employee_name"), "Ashton Cox");
		softAssert.assertEquals(json.getString("data[3].employee_name"), "Cedric Kelly");
		softAssert.assertEquals(json.getString("data[4].employee_name"), "Airi Satou");
		
		//2.yol olabilir loop ve list ile yapilir 
		
		List<String> isimList=new ArrayList<>();
		
		isimList.add("Tiger Nixon");
		isimList.add("Garrett Winters");
		isimList.add("Ashton Cox");
		isimList.add("Cedric Kelly");
		isimList.add("Airi Satou");
		
		for(int i=0; i<isimList.size(); i++) {
			
			softAssert.assertEquals(json.getString("data["+i+"].employee_name"), isimList.get(i));
			                        //data["+i+"].employee_name bize  json da ki data yi verir verir isimList de ilgili ismi alir 
		}
		
		
		//3. yol en iyisi 
		List<Map> actualList=json.getList("data"); // json da ki  getList medodunu kullnara data lar list eklendi
		
		System.out.println(actualList);
		
		Map<Integer,String>  expectedMap= new HashMap<>();
		
		expectedMap.put(0, "Tiger Nixon");
		expectedMap.put(1, "Garrett Winters");
		expectedMap.put(1, "Ashton Cox");
		expectedMap.put(1, "Cedric Kelly");
		expectedMap.put(1, "Airi Satou");
		System.out.println(expectedMap);
		
		for ( int i=0; i< expectedMap.size(); i++ ) {
			
			softAssert.assertEquals(actualList.get(i).get("employee_name"), expectedMap.get(i));
			
		}
		
		// 4.yol 
		List<String> firstfive = new ArrayList<String>(); 
		firstfive.add("Tiger Nixon");
		firstfive.add("Garrett Winters");
		firstfive.add("Ashton Cox");
		firstfive.add("Cedric Kelly");
		firstfive.add("Airi Satou");
		
		SoftAssert softAssert1 = new SoftAssert();
		softAssert1.assertEquals(json.getList("data[0,1,2,3,4].employee_name" ),firstfive);
		softAssert1.assertAll();
		
		
		softAssert.assertAll();
		
	}
	
	
	  

}
