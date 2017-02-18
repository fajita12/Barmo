package Mechanics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import static com.mashape.unirest.http.Unirest.post;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
/**
 *
 * @author Brad Rogers
 */
public class Main {

    public static String customer = "accounts";
    public static String createCustomer = "";
    public static String baseURL = "http://api.reimaginebanking.com/";
    public static String APIKey = "?key=317e9cc5ca5935508741dfe84740f0eb";
    public static String url = baseURL = "customers" + APIKey;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        JSONObject body = new JSONObject();
        body.append("first_name", "brad");
        body.append("last_name", "rogers");
        JSONObject address = new JSONObject();
        address.append("street_number", "string");
        address.append("street_name", "string");
        address.append("city", "string");
        address.append("state", "string");
        address.append("zip", "string");
        body.append("address", address);
        try {
            HttpResponse<JsonNode> jsonResponse;
            jsonResponse = post(url)
                    .header("accept", "application/json")
                    .body(body)
                    .asJson();
        } catch (UnirestException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
