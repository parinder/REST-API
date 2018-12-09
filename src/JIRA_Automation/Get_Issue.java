package JIRA_Automation;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class Get_Issue {

	public void gIssue() {

		RestAssured.baseURI = "http://localhost:1112";
		Response resp = given().when().get("/rest/api/2/issue/10101").then()
				.assertThat().and().statusCode(200).and().contentType(ContentType.JSON).and()
				.body("id", equalTo("10101")).and().extract().response();

		String respString = resp.asString();
		JsonPath js = new JsonPath(respString);
		String keyID = js.get("key");
		System.out.println(keyID);

	}

}
