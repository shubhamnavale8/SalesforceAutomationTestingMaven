package com.qa.testrailmanager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

public class TestRailManager {

	public static String TEST_RUN_ID="3";
	public static String TEST_RAIL_USERNAME ="shubham.navale@enzigma.com" ;
	public static String TEST_RAIL_PASSWORD ="Salesforce@8308775266" ;
	public static String TEST_RAIL_ENGIN_URL="https://shubhamautomationtestingonselenium.testrail.io/";
	
	public static int TEST_CASE_PASS_STATUS=1;
	public static int TEST_CASE_FAIL_STATUS=5;
	
	public static void addResultsForTestCase(String testCaseId, int status, String error) throws MalformedURLException, IOException, APIException {
		System.out.print("Test Case Id "+testCaseId);
		String testRunId=TEST_RUN_ID;
		APIClient client=new APIClient(TEST_RAIL_ENGIN_URL);
		client.setUser(TEST_RAIL_USERNAME);
		client.setPassword(TEST_RAIL_PASSWORD);
		
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("status_id", status);
		data.put("comment","this test is executed via open cart test automation code "+error);
		
		client.sendPost("add_result_for_case/"+TEST_RUN_ID+"/"+testCaseId, data);
	}

}
