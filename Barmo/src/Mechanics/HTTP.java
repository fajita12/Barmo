/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
/**
 *
 * @author Brad Rogers
 */
public class HTTP {
        
    public static String baseURL = "http://api.reimaginebanking.com/";
    public static String APIKey = "?key=317e9cc5ca5935508741dfe84740f0eb";

    public static void main(String[] args) throws Exception {
        HTTP http = new HTTP();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet("customers");

        System.out.println("\nTesting 2 - Send Http POST request");
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
        http.sendPost("customers", body);
    }

	// HTTP GET request
    private String sendGet(String cmd) throws Exception {

        String url = baseURL + cmd + APIKey;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        con.setRequestProperty("content-type","application/json");

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());
        return(response.toString());
	}

	// HTTP POST request
    private String sendPost(String cmd, JSONObject body) throws Exception {

        String url = baseURL + cmd + APIKey;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setDoOutput(true);
            
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Accept", "application/json");
            
        con.setRequestMethod("POST");

        OutputStream os = con.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(body.toString());
        osw.flush();
        osw.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        StringBuffer response = new StringBuffer();
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
        }catch(IOException o){
            System.out.println(o.getMessage());
        }
        return(response.toString());
    }
    
}
