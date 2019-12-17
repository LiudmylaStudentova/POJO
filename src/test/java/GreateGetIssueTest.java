import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JiraAPISteps;

import static junit.framework.Assert.assertEquals;

public class GreateGetIssueTest {
    String summary = "QA Automation Test Summary";
    String assignee = "webinar5";

    @Test
    public void creategetIssue(){

        //create issue in Jira
        Response response = JiraAPISteps.createIssue();
        assertEquals(response.statusCode() , 201);
        String issueKey =  response.then().extract().path("key");

        //get issue in Jira
        JiraAPISteps.getIssue(issueKey);
    }
}

