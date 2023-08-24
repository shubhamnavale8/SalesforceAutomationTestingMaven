package com.salesforcetesting.salesforce_automation_testing;

import java.io.FileNotFoundException;

import documentationfiles.SalesforceLoginCredentials;
import login.GenerateSalesforceLoginURL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        System.out.println( "Hello World!" );
        
        GenerateSalesforceLoginURL login=new GenerateSalesforceLoginURL("shubham@enzigma.in","Salesforce@8308775266uOENBzHqEoQolEFQFdN4lZuV","https://login.salesforce.com","/services/oauth2/token?grant_type=password","3MVG9n_HvETGhr3BXTlhoU0lpVM8RUgk35RmjNMRiNL3luQqQu0LlCokwnv449ymiLzg8eo1tDKKK2pd6VCNq","B36B2F05FCE3784086EFB3D99958CE44F098051CCB558E9EE2B6D847501B1D6A");
        System.out.println(login.getLoginURL("getSessionId"));
        
        String pathFile="C:\\Users\\shubham.navale\\Desktop\\Salesforce_Automation_ExcelSheets\\SalesforceLoginCredentials.xlsx";
        SalesforceLoginCredentials ss=new SalesforceLoginCredentials(1,pathFile);
        
        System.out.println(ss.getUSERNAME());
        System.out.println(ss.getPASSWORD());
        System.out.println(ss.getLOGINURL());
        System.out.println(ss.getGRANTSERVICE());
        System.out.println(ss.getCLIENTSECRET());
        System.out.println(ss.getCLIENTID());
        System.out.println(ss.getAfterInstanceUrlUptoSID());
    }
}
