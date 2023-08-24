package com.salesforcetesting.salesforce_automation_testing;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.gurock.testrail.APIException;
import com.qa.testrailmanager.TestRailManager;

import documentationfiles.SalesforceLoginCredentials;
import login.GenerateSalesforceLoginURL;


public class TestCaseSetup {
	public static String filePath,TestLabel,testCaseId;
	public static String loginUrl;
    int TestNo;
    TestCaseSetup(){
        this.TestNo=0;
    }
    
    @BeforeTest
    public void beforAllTestSuite() throws InterruptedException{
    	// String pathFile="C:\\Users\\shubham.navale\\Desktop\\Salesforce_Automation_ExcelSheets\\SalesforceLoginCredentials.xlsx";
        // SalesforceLoginCredentials ss=new SalesforceLoginCredentials(1,pathFile);
        // System.out.println(ss.getUSERNAME());
        // System.out.println(ss.getPASSWORD());
        // System.out.println(ss.getLOGINURL());
        // System.out.println(ss.getGRANTSERVICE());
        // System.out.println(ss.getCLIENTSECRET());
        // System.out.println(ss.getCLIENTID());
        // System.out.println(ss.getAfterInstanceUrlUptoSID());
    	GenerateSalesforceLoginURL login=new GenerateSalesforceLoginURL("shubham@enzigma.in","Salesforce@8308775266uOENBzHqEoQolEFQFdN4lZuV",	"https://login.salesforce.com",	"/services/oauth2/token?grant_type=password","3MVG9n_HvETGhr3BXTlhoU0lpVM8RUgk35RmjNMRiNL3luQqQu0LlCokwnv449ymiLzg8eo1tDKKK2pd6VCNq",	"B36B2F05FCE3784086EFB3D99958CE44F098051CCB558E9EE2B6D847501B1D6A",	"//secur/frontdoor.jsp?sid=");
    	loginUrl=login.getLoginURL("getSessionId");
    }
    
	 @BeforeMethod
	    public void loginToTheSalesforce() throws InterruptedException{
	        TestNo++;
	        System.out.println("Test Number "+TestNo+" Started");
	        
	        GenerateSalesforceLoginURL login=new GenerateSalesforceLoginURL("shubham@enzigma.in","Salesforce@8308775266uOENBzHqEoQolEFQFdN4lZuV","https://login.salesforce.com","/services/oauth2/token?grant_type=password","3MVG9n_HvETGhr3BXTlhoU0lpVM8RUgk35RmjNMRiNL3luQqQu0LlCokwnv449ymiLzg8eo1tDKKK2pd6VCNq","B36B2F05FCE3784086EFB3D99958CE44F098051CCB558E9EE2B6D847501B1D6A");
	        System.out.println(login.getLoginURL("getSessionId"));
	        loginUrl=login.getLoginURL("getSessionId");
	    }

	    @AfterMethod
	    public void logoutFromSalesforce(ITestResult result) throws MalformedURLException, IOException, APIException{

	        if(result.getStatus() == ITestResult.SUCCESS){
	            //TestCaseSetup.changeStatus("Pass", filePath, TestLabel);
	            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_PASS_STATUS, "");
	        }
	        else{
	            //TestCaseSetup.changeStatus("Fail", filePath, TestLabel);
	            TestRailManager.addResultsForTestCase(testCaseId, TestRailManager.TEST_CASE_FAIL_STATUS, "");
	        }
	        
	    }
}
