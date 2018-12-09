package Post_Request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class addPlacEdelete {
    @Test
	public void addplacedelete() {
		
		RestAssured.baseURI="http://216.10.245.166";
	Response resp =	given().queryParam("key", "qaclick123").body("{\n" + 
				" \"location\":{\n" + 
				" \"lat\" : -38.383494,\n" + 
				" \"lng\" : 33.427362\n" + 
				"},\n" + 
				" \"accuracy\":50,\n" + 
				" \"name\":\"Frontline house\",\n" + 
				" \"phone_number\":\"(+91) 983 893 3937\",\n" + 
				" \"address\" : \"29, side layout, cohen 09\",\n" + 
				" \"types\": [\"shoe park\",\"shop\"],\n" + 
				" \"website\" : \"http://google.com\",\n" + 
				" \"language\" : \"French-IN\"\n" + 
				"}").when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
	
	String respString = resp.asString();
	System.out.println(respString);
	JsonPath js=new JsonPath(respString);
	String placeID=js.get("place_id");
	System.out.println(placeID);
	
	given().queryParam("key", "qaclick123").body("{\n" + 
			"	\"place_id\": \""+placeID+"\"" + 
			"}").when().post("/maps/api/place/delete/json").then().assertThat().statusCode(200).contentType(ContentType.JSON)
	.body("status", equalTo("OK"));
	}
}
