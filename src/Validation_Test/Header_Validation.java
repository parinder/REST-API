package Validation_Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Header_Validation {

	@Test
	public void header_validate() {

		RestAssured.baseURI = "http://216.10.245.166";
		given().queryParam("key", "qaclick123")
				.body("{\n" + " \"location\":{\n" + " \"lat\" : -38.383494,\n" + " \"lng\" : 33.427362\n" + "},\n"
						+ " \"accuracy\":50,\n" + " \"name\":\"Frontline house\",\n"
						+ " \"phone_number\":\"(+91) 983 893 3937\",\n"
						+ " \"address\" : \"29, side layout, cohen 09\",\n" + " \"types\": [\"shoe park\",\"shop\"],\n"
						+ " \"website\" : \"http://google.com\",\n" + " \"language\" : \"French-IN\"\n" + "}")
				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).and().header("server", "Apache").and().body("status", equalTo("OK"));

	}

}
