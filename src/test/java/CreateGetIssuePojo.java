import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.issue.Issue;
import utils.JiraAPISteps;
import utils.JiraPojoHelper;

import static org.testng.Assert.assertEquals;

public class CreateGetIssuePojo {
    String projectId="11400";
    String issueType="10107";

    @Feature("Create and Get Issue Jira")
    @Test

    public void createIssueTest() {
        String summary = "QA Automation Test from Mila";
        String assignee = "webinar5";


       Issue issuePOJO = JiraPojoHelper.generateJSONForIssue(projectId, summary, issueType, assignee);

        // create issue in Jira
    // String issueKey = JiraAPISteps.createIssue(issuePOJO);

        // get issue in Jira
       // JiraAPISteps.getIssue(issueKey);
    }}
