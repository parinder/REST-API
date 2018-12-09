package JIRA_Automation;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class Delete_Issue {

	public void gIssue() {

		RestAssured.baseURI = "http://localhost:1112";
		Response resp = given().header("Content-Type", "application/json")
				.body("{\n" + "	\"username\": \"parindersinghraina\", \"password\": \"P@rinder55\"\n" + "}").when()
				.post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response();
		String respString = resp.asString();
		JsonPath js = new JsonPath(respString);
		String SessionID = js.get("session.value");
		System.out.println(SessionID);

		Response respdel = given().header("Content-Type", "application/json")
				.header("Cookie", "JSESSIONID=" + SessionID)
	.when().delete("/rest/api/2/issue/10124").then().assertThat().and().statusCode(204).and()
				.extract().response();
		String respStringdel=respdel.asString();
		System.out.println(respStringdel);
	}

}
