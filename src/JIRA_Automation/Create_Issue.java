package JIRA_Automation;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Create_Issue {
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
				.body("{\n" + "	\"fields\": {\n" + "		\"project\":\n" + "		{\n"
						+ "			\"key\": \"RES\"\n" + "			\n" + "			},\n"
						+ "			\"summary\": \"JIRA REST API\",\n"
						+ "			\"description\": \"This is first Issue\",\n" + "			\"issuetype\": {\n"
						+ "				\"name\": \"Bug\"\n" + "			}\n" + "			\n" + "		}\n"
						+ "	}")
				.when().post("/rest/api/2/issue").then().assertThat().statusCode(201).extract().response();

		String respIssueIdString = respIssueId.asString();
		JsonPath jsIssue = new JsonPath(respIssueIdString);
		String IssueID = jsIssue.get("id");
		System.out.println(IssueID);
		
	}

}
