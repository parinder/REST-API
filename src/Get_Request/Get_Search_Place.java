package Get_Request;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class Get_Search_Place {
	
	
	@Test
	public void search() {
		RestAssured.baseURI="https://maps.googleapis.com";
		given().param("key", "AIzaSyD9W0DK05yShaxOGSOVZ6O5E8OseR6liGE").param("query", "restaurants+in+Halifax").
		when().get("/maps/api/place/textsearch/json").then().assertThat().statusCode(200).contentType(ContentType.JSON).and()
		.body("results[0].name", equalTo("The Bicycle Thief"));
		System.out.println("TEST PASSED");
	}

}
