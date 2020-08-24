package techproedturkish01.techproedturkish01api;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class PutRequest01 extends TestBase {

	
	@Test
	public void put01() {
		Response response=given().
				                spec(spec03).
				          when().
				                get("/200");
		
		response.prettyPrint();
		
		JSONObject jsonObject=new JSONObject();
		
		jsonObject.put("userId", 90);
		jsonObject.put("title", "can");
		jsonObject.put("id", 555);
		jsonObject.put("completed",true);
		
		Response responseAfterPut=given().
				                          contentType(ContentType.JSON).
				                          spec(spec03).
				                          body(jsonObject.toString()).
				                  when().
				                        put("/200");
		
		responseAfterPut.prettyPrint();
		
	
		
	}
}
