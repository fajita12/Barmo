/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infrastructure;

import Infrastructure.MapManager.unit_temp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;

/**
 *
 * @author Brad Rogers
 */
public class Bot {
    
    private static int static_id = 0;
    private int person_id;
    
    private static String key = "839b5d4aa43517dd";
    private static String URL_Start = "http://api.wunderground.com/api/";
    private State state;
    private Town town;
    
    private unit_temp temp_type;
    
    public Bot(Town town, unit_temp temp_type){
        this.person_id = static_id;
        static_id += 1;
        
        this.town = town;
        this.state = this.town.getState();
        this.temp_type = temp_type;
    }
    
    public String contactServer(String type) throws MalformedURLException, IOException{
        String cmd = URL_Start + key + "/" + type + "/q/" + getState().getShortname() + "/" + getTown().getName() + ".json";
        
        URL cmdURL = new URL(cmd);
        URLConnection cmdConnection = cmdURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(cmdConnection.getInputStream()));
        String inputLine;
        
        String weatherResults = "";
        while ((inputLine = in.readLine()) != null) 
            weatherResults += inputLine + "\n";
        in.close();
        
        return weatherResults;
    }
    
    public JSONObject getConditions(){
        String serverResults;
        try {
            serverResults = contactServer("conditions");
        } catch (IOException ex) {
            return null;
        }
        JSONObject obj = new JSONObject(serverResults);
        return obj;
    }
    
    public JSONObject getHourly(){
        String serverResults;
        try {
            serverResults = contactServer("hourly");
        } catch (IOException ex) {
            return null;
        }
        JSONObject obj = new JSONObject(serverResults);
        return obj;
    }
    
    public String getShortConditionString(){
        JSONObject conditions = getConditions();
        JSONObject curr_ob = conditions.getJSONObject("current_observation");
        String full = curr_ob.getJSONObject("display_location").getString("full");
        String weather = curr_ob.getString("weather");
        String temp = curr_ob.getString("temperature_string");
        String observation_time = curr_ob.getString("observation_time_rfc822");
        return full + " @" + observation_time + " -- " + weather + " " + temp;
    }
    
    public String getConditionsClothingString(){
        JSONObject conditions = getConditions();
        Clothing_Result cloths = new Clothing_Result(Clothing_Result.result_type.CURRENT, conditions, this.temp_type);
        return cloths.toString();
    }
    
    public int getID(){
        return this.person_id;
    }
    
    private String getKey(){
        return this.key;
    }
    
    public State getState(){
        return this.state;
    }
    
    public Town getTown(){
        return this.town;
    }
    
    public unit_temp getTemp_type(){
        return this.temp_type;
    }
    
    public int setState(State newState){
        if(getState() == null){
            if(newState == null){
                return -1;
            }else{
                this.state = newState;
                return 1;
            }
        }
        if(newState.equals(getState()) || newState == null){
            return -1;
        }else{
            this.state = newState;
            return 1;
        }
    }
    
    public int setTown(Town newTown){
        if(getTown() == null){
            if(newTown == null || !newTown.getState().equals(getState())){
                return -1;
            }else{
                this.town = newTown;
                return 1;
            }
        }
        if(newTown.equals(getTown()) || newTown == null || !newTown.getState().equals(getState())){
            return -1;
        }else{
            this.town = newTown;
            return 1;
        }
    }
    
    public int setTemp_type(unit_temp newTemp_type){
        if(newTemp_type == null || newTemp_type.equals(getTemp_type())){
            return -1;
        }else{
            this.temp_type = newTemp_type;
            return 1;
        }
    }
    
    @Override
    public String toString(){
       return "Bot #" + getID() + " " + getState().toString() + " " + getTown().toString();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj != null && obj.getClass() == this.getClass()){
            if(((Bot)obj).getID() == this.getID()){
                return true;
            }
        } 
        return false;
    }
}
