

import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matcher;
import org.hamcrest.text.MatchesPattern;
import org.testng.annotations.Test;
import pojo.issue.Credentials;
import pojo.issue.Issue;
import utils.Authorization;
import utils.JiraPojoHelper;

import java.util.regex.Pattern;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CreateDeleteIssuePOJOTest {
    String projectId="11400";
    String issueType="10107";

    @Feature("Create and Delete Issue Jira")
    @Test(groups = {"Regression"})
    public void createAndDeleteIssue(){


        String summary = "Summary POJO ";
        String assignee = "webinar5";

        //Credentials credentialsPOJO = JiraPojoHelper.generateJSONForLogin(Authorization.username, Authorization.password);

        Issue issuePOJO = JiraPojoHelper.generateJSONForIssue(projectId, summary, issueType, assignee); // заменяем код с body issueJSON на issuePOJO

        //create issue
        Response response = given().
                auth().preemptive().basic("webinar5", "webinar5").
                header("Content-Type", "application/json").
                body(issuePOJO).
                when().
                post("https://jira.hillel.it/rest/api/2/issue").
                then().
                extract().response();
        assertEquals(201, response.statusCode());
        System.out.println("Status code:" + response.statusCode());
        String responseBody = response.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBody);
        String issueKey =  response.then().extract().path("key");//получаем ключ


       //Delete issue
        Response deleteIssue = given().
                auth().preemptive().basic("webinar5", "webinar5").
                when().
                delete("https://jira.hillel.it/rest/api/2/issue/"+issueKey).
                then().
                extract().response();
        assertEquals(deleteIssue.statusCode(), 204);
        System.out.println("\nStatus code:" + deleteIssue.statusCode());

      //проверка на то, что мы хотим получить только что удаленный тикет
        Response getDeletedIssue=given().
                auth().preemptive().basic("webinar5", "webinar5").
                header("Content-Type", "application/json").
                body(issuePOJO).
                when().
                get("https://jira.hillel.it/rest/api/2/issue/" + issueKey).
                then().
                extract().response();
        assertEquals(getDeletedIssue.statusCode(), 404);
        System.out.println("Status code:" + getDeletedIssue.statusCode());
        String responseBodygetDelete = getDeletedIssue.then().extract().asString();
        System.out.printf("\nRESPONSE: " + responseBodygetDelete);


    }
}


