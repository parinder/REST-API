package JIRA_Automation;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Update_Comment {
	@Test
	public void auth() {

		RestAssured.baseURI = "http://localhost:1112";
		Response resp = given().header("Content-Type", "application/json")
				.body("{\n" + "	\"username\": \"parindersinghraina\", \"password\": \"P@rinder55\"\n" + "}").when()
				.post("/rest/auth/1/session").then().assertThat().statusCode(200).extract().response();
		String respString = resp.asString();
		JsonPath js = new JsonPath(respString);
		String SessionID = js.get("session.value");
		System.out.println(SessionID);

		Response respIssueId = given().header("Content-Type", "application/json")
				.header("Cookie", "JSESSIONID=" + SessionID)
				.body("{\n" + 
						"    \"body\": \"Hey I'm Add Comment then UPDATE in Issue RES-7\",\n" + 
						"    \"visibility\": {\n" + 
						"        \"type\": \"role\",\n" + 
						"        \"value\": \"Administrators\"\n" + 
						"    }\n" + 
						"}")
				.when().put("/rest/api/2/issue/10129/comment/"+Add_Comment.auth()).then().assertThat().statusCode(200).extract().response();

		
	}

}
