package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class PutRequest02 extends TestBase {

	
	@Test
	public void put01() {
		Response response=given().
				                spec(spec03).//degistirmeden onceki datayi gormek icin yazdik
				          when().
				                get("/180");
		
		response.prettyPrint();
		
		JSONObject jsonObject=new JSONObject();// burada yeni bir json objesi olusturuduk 
		
		jsonObject.put("userId", 90);
		jsonObject.put("title", "can");
		jsonObject.put("id", 555);
		jsonObject.put("completed",true);
		
		Response responseAfterPut=given().
				                          contentType(ContentType.JSON).
				                          spec(spec03).
				                          body(jsonObject.toString()).
				                  when().
				                        put("/200");// put ile  dataBase gonderdirk ve update ettik 
		
		responseAfterPut.prettyPrint();
		
		responseAfterPut.
	                    then().// hard assertion  assert ettim..status icin 
	                    assertThat().
	                    statusCode(200);// status code 201 de olabilir  swager documantationda  denilen syler bizim  rehberimiz
		JsonPath json=responseAfterPut.jsonPath();
		SoftAssert sa=new SoftAssert();
		
		//completed  degereini verify ediniz
		//sa.assertEquals(json.getBoolean("completed"), true);
		sa.assertEquals(json.getBoolean("completed"), jsonObject.get("completed"));// yukaridan  aldik ve dinamik yaptik
		                 // var olan data               benim elimde ki data expectedt olan 
		
		//title degerini  verify ediniz
		sa.assertEquals(json.getString("title"), jsonObject.get("title"));
		
		//userId degerini verify ediniz 
		sa.assertEquals(json.getInt("userId"), jsonObject.get("userId"));
		
		sa.assertAll();
		
		
	
		
	}
	
}
