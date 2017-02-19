/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mechanics;

import org.json.JSONObject;

/**
 *
 * @author Brad Rogers
 */
public class Tester {
    
    public static HTTP globalHTTP;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
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
    
}
