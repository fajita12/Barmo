/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
/**
 *
 * @author Brad Rogers
 */
public class Test {

	private final String USER_AGENT = "Mozilla/5.0";
        
        public static String baseURL = "http://api.reimaginebanking.com/";
    
    public static String APIKey = "?key=317e9cc5ca5935508741dfe84740f0eb";

	public static void main(String[] args) throws Exception {

		Test http = new Test();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet("customers");

		System.out.println("\nTesting 2 - Send Http POST request");
		http.sendPost();

	}

	// HTTP GET request
	private void sendGet(String cmd) throws Exception {

		String url = baseURL + cmd + APIKey;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//con.setRequestProperty("User-Agent", USER_AGENT);
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
	private void sendPost() throws Exception {

            String url = baseURL + "customers" + APIKey;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setDoOutput(true);
            //con.setDoInput(true);
            
            con.setRequestProperty("Content-Type","application/json");
            con.setRequestProperty("Accept", "application/json");
            
            con.setRequestMethod("POST");

            // Send post request
            JSONObject body = new JSONObject();
            body.put("first_name", "brad");
            body.put("last_name", "rogers");
            JSONObject address = new JSONObject();
            address.put("street_number", "string");
            address.put("street_name", "string");
            address.put("city", "string");
            address.put("state", "WI");
            address.put("zip", "53703");
            body.put("address", address);
            System.out.println(body.toString());

            //DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            OutputStream os = con.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(body.toString());
            osw.flush();
            osw.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            //System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            try{
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());
            }catch(IOException o){
                System.out.println(o.getMessage());
            }

	}
    
}
