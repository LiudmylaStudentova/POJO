package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojo.issue.Fields;
import pojo.issue.Issue;
import pojo.issue.IssueType;
import pojo.issue.Project;

public class Main {

    public static  Issue generateJSONForIssue(String projectId, String summary, String issueType, String assignee) {

        Issue issuePOJO = new Issue(new Fields()
                                            .setProject(projectId)
                                            .setSummary(summary)
                                            .setAssignee(assignee)
                                            .setIssueType(issueType)
        );
        return issuePOJO;
    }

//    public static void main(String[] args) {
//        Project project = new Project("10000");
//        String resultingJSON = extractPOJO(project);
//        System.out.println("RESULT:" + resultingJSON);
//
//    }

    public static String extractPOJO(Object pojo) {
        ObjectMapper mapper = new ObjectMapper();
        String result = null;
        try {
            result = mapper.writeValueAsString(pojo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}


//        JSONObject newIssueJSON = new JSONObject();
//        JSONObject fields = new JSONObject();
//        fields.put("summary", "Test Summary");
//        JSONObject issuetype = new JSONObject();
//        issuetype.put("id", "10107");
//        JSONObject project = new JSONObject();
//        project.put("id", "11400");
//        JSONObject assignee = new JSONObject();
//        assignee.put("name", "webinar5");
//
//        fields.put("issuetype", issuetype);
//        fields.put("project", project);
//        fields.put("assignee", assignee);
//
//        newIssueJSON.put("fields", fields);
//        System.out.println(newIssueJSON.toJSONString());

