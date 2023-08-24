package login;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GenerateSalesforceLoginURL {
	private static  String USERNAME;
	private static String PASSWORD;
	private static String LOGINURL;
	private static String GRANTSERVICE;
	private static String CLIENTID;
	private static String CLIENTSECRET;
	private static String AfterInstanceUrlUptoSID;
	
	public GenerateSalesforceLoginURL(String USERNAME,String PASSWORD,String LOGINURL,String GRANTSERVICE,String CLIENTID,String CLIENTSECRET){
		this.USERNAME=USERNAME;
		this.PASSWORD=PASSWORD;
		this.LOGINURL=LOGINURL;
		this.GRANTSERVICE=GRANTSERVICE;
		this.CLIENTID=CLIENTID;
		this.CLIENTSECRET=CLIENTSECRET;
	}
	
	public GenerateSalesforceLoginURL(String USERNAME,String PASSWORD,String LOGINURL,String GRANTSERVICE,String CLIENTID,String CLIENTSECRET,String AfterInstanceUrlUptoSID){
		this.USERNAME=USERNAME;
		this.PASSWORD=PASSWORD;
		this.LOGINURL=LOGINURL;
		this.GRANTSERVICE=GRANTSERVICE;
		this.CLIENTID=CLIENTID;
		this.CLIENTSECRET=CLIENTSECRET;
		this.AfterInstanceUrlUptoSID=AfterInstanceUrlUptoSID;
	}
	
	public  String getLoginURL(String classNameofQuery) {
		if(AfterInstanceUrlUptoSID==null) {
			AfterInstanceUrlUptoSID="//secur/frontdoor.jsp?sid=";
		}
		CloseableHttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
		String loginURL = LOGINURL + GRANTSERVICE + "&client_id=" + CLIENTID +
						"&client_secret=" + CLIENTSECRET +
						"&username=" + USERNAME + "&password=" + PASSWORD;
        HttpPost httpPost = new HttpPost(loginURL);
        HttpResponse response = null;
        try {
            // Execute the login POST request
            response = httpclient.execute(httpPost);

        } catch (ClientProtocolException cpException) {
            cpException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        final int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode != HttpStatus.SC_OK) {
            System.out.println("Error authenticating to Force.com: " + statusCode);
        }

        String getResult = null;

        try {
            getResult = EntityUtils.toString(response.getEntity());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        JSONObject jsonObject = null;
        String loginAccessToken = null;
        String loginInstanceUrl = null;

        try {
            jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
            loginAccessToken = jsonObject.getString("access_token");
            loginInstanceUrl = jsonObject.getString("instance_url");
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        System.out.println(response.getStatusLine());
        System.out.println("Successful login");
        System.out.println("instance URL: " + loginInstanceUrl);
        System.out.println("access token/session ID: " + loginAccessToken);

        // Construct the URL to call your getSessionId Apex class
        String sessionIdUrl = loginInstanceUrl + "/services/apexrest/"+classNameofQuery;
        HttpGet httpGet = new HttpGet(sessionIdUrl);
        httpGet.setHeader("Authorization", "Bearer " + loginAccessToken);
        HttpResponse sessionIdResponse = null;
        try {
            // Execute the HTTP GET request to getSessionId REST resource
            sessionIdResponse = httpclient.execute(httpGet);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String sessionIdResult="";
        int sessionIdStatusCode = sessionIdResponse.getStatusLine().getStatusCode();
        if (sessionIdStatusCode != HttpStatus.SC_OK) {
            System.out.println("Error calling getSessionId: " + sessionIdStatusCode);
        } else {
            try {
                sessionIdResult = EntityUtils.toString(sessionIdResponse.getEntity());
                System.out.println("getSessionId response: " + sessionIdResult);
                System.out.println(loginInstanceUrl+AfterInstanceUrlUptoSID+sessionIdResult.replace("\"", ""));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        httpPost.releaseConnection();
        return loginInstanceUrl+AfterInstanceUrlUptoSID+sessionIdResult.replace("\"", "");
		
	}
}
