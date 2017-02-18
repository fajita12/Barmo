package Mechanics;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Brad Rogers
 */
public class HTTP {
    
    public static String baseURL = "http://api.reimaginebanking.com/";
    
    public static String APIKey = "?key=317e9cc5ca5935508741dfe84740f0eb";
    
    public static String sendRequest(String cmd) throws MalformedURLException, IOException{
        URL cmdURL = new URL(baseURL + cmd + APIKey);
        URLConnection cmdConnection = cmdURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(cmdConnection.getInputStream()));
        String inputLine;
        
        String requestResult = "";
        while ((inputLine = in.readLine()) != null) 
            requestResult += inputLine + "\n";
        in.close();
        
        return requestResult;
    }
    
    public static void sendGet(String cmd) throws Exception {

                String url = baseURL + cmd + APIKey;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("content-type","application/json");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
    public static void sendPost(String cmd) throws Exception {

                String url = baseURL + cmd + APIKey;
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", USER_AGENT);
		//con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("content-type","application/json");

		String urlParameters = "code=0";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());

	}
}
