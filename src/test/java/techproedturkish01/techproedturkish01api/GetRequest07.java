package techproedturkish01.techproedturkish01api;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.response.Response;

public class GetRequest07 extends TestBase{

	/*
	 * Among the data there are someones whose first name is “Susan”
	 * url spec01 from TestBase
	 */
	@Test
	public void get01() {
		Response response=given().//spec kullanmak icin extends yapmak zorundayiz 
				               spec(spec01).// main url test base de ilave varsa asagisa eklnier 
				          when().
				               get("/booking?firstname=Susan");//parantez icin string yazmak iyi deil
		response.prettyPrint();
		
		assertTrue(response.getBody().asString().contains("bookingid"));
	}
	@Test
	public void get02() {
		spec01.queryParam("firstname", "Susan");//base UrL DEN sorn ?firstname=Susan yazar ve aratir
		spec01.queryParam("depositpaid", true);
		
		Response response=given().//spec kullanmak icin extends yapmak zorundayiz 
				               spec(spec01).// main url test base de ilave varsa asagisa eklnier 
				          when().
				               get("/booking");//parantez icin string yazmak iyi deil
		response.prettyPrint();
		
		assertTrue(response.getBody().asString().contains("bookingid"));
	}
}
