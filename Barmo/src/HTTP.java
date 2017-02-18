
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

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
    public static String sendRequest(String cmd) throws MalformedURLException, IOException{
        URL cmdURL = new URL(cmd);
        URLConnection cmdConnection = cmdURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(cmdConnection.getInputStream()));
        String inputLine;
        
        String requestResult = "";
        while ((inputLine = in.readLine()) != null) 
            requestResult += inputLine + "\n";
        in.close();
        
        return requestResult;
    }
}
