package Centeralization;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class addPlacEdelete {

	Properties prop = new Properties();

	@Test
	public void addplacedelete() throws IOException {

		FileInputStream fis = new FileInputStream(
				"/home/parinder/eclipse-workspace/Testing_Projects/REST_API/src/Centeralization/DataDriven.properties");
		
		prop.load(fis);

		RestAssured.baseURI = prop.getProperty("HOST");
		Response resp = given().queryParam(prop.getProperty("key"))
				.body(Body_Add_Delete.body_add())
				.when().post(prop.getProperty("ResourceAdd")).then().assertThat().statusCode(200)
				.contentType(ContentType.JSON).body("status", equalTo("OK")).extract().response();

		String respString = resp.asString();
		System.out.println(respString);
		JsonPath js = new JsonPath(respString);
		String placeID = js.get("place_id");
		System.out.println(placeID);

		given().queryParam(prop.getProperty("key")).body("{\n" + "	\"place_id\": \"" + placeID + "\"" + "}").when()
				.post(prop.getProperty("ResourceDel")).then().assertThat().statusCode(200).contentType(ContentType.JSON)
				.body("status", equalTo("OK"));
	}
}
