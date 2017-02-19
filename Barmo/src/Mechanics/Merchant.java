package Mechanics;

import static Mechanics.Tester.globalHTTP;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;


public class Merchant {

	private String id;
	private String name;
	private String category;
	private String[] address;
	private String lat;
	private String lng;
	
	public Merchant(String id) {
            this.id = id;
            try {
                JSONObject obj = new JSONObject(globalHTTP.sendGet("merchants/" + id));
                this.name = obj.getString("name");
                this.category = obj.getString("category");
                address = new String[5];
                JSONObject JSONaddress = obj.getJSONObject("address");
                this.address[0] = JSONaddress.getString("street_number");
                this.address[1] = JSONaddress.getString("street_name");
                this.address[2] = JSONaddress.getString("city");
                this.address[3] = JSONaddress.getString("state");
                this.address[4] = JSONaddress.getString("zip");
                JSONObject geocode = obj.getJSONObject("geocode");
                this.lat = geocode.getString("lat");
                this.lng = geocode.getString("lng");
            } catch (Exception ex) {
                
            }
		// post /merchants
	
	}
	
	public int updateMerchant(JSONObject updateData) {
            String cmd = "merchants/" + this.id; 
            try {
                globalHTTP.sendPut(cmd, updateData);
                // put /merchants/{id}
            } catch (Exception ex) {
                return -1;
            }
            return 0;
	}
        
        public int sendTransaction(Customer user, Purchase purchase){
            try{
                globalHTTP.sendPost("accounts/" + user.getAccountId() + "/purchases", purchase.toJSON());
            }catch(Exception ex){
                return -1;
            }
            return 0;
        }
        
        public int updateTransaction(Customer user, Purchase currPurchase, String addedDescription, double amount){
            try{
                int i;
                JSONObject body = new JSONObject();
                String[] itemized = currPurchase.getDescription().split("\n");
                String[] itemized_description = addedDescription.split("\n");
                String[] itemized_new = new String[itemized.length + itemized_description.length];
                for(i = 0; i < itemized.length - 4; i++){
                    itemized_new[i] = itemized[i];
                }
                for(i = 0; i < itemized_description.length; i++){
                    itemized_new[i + itemized.length - 4] = itemized_description[i];
                }
                int curr = itemized.length - 4 + itemized_description.length;
                itemized_new[curr] = "--------------------";
                itemized_new[curr + 1] = "Total: $" + currPurchase.getAmount() + amount;
                itemized_new[curr + 2] = itemized[itemized.length - 2];
                itemized_new[curr + 3] = "--------------------";
                
                String description = "";
                for(i = 0; i < itemized_new.length; i++){
                    description = itemized_new[i] + "\n";
                }
                
                body.put("description", description);
                body.put("amount", currPurchase.getAmount() + amount);
                globalHTTP.sendPost("purchase/" + currPurchase.getPurchaseId(), body);
            }catch(Exception ex){
                return -1;
            }
            return 0;
        }
        
        public int deleteTransaction(Customer user, Purchase purchase){
            try{
                //not impletmented
            }catch(Exception ex){
                
            }
            return 0;
        }
        
        public JSONObject getTransactions(){
            try {
                return new JSONObject(globalHTTP.sendGet("merchants/" + this.id + "/purchases"));
            } catch (Exception ex) {
                return null;
            }
        }
        
        public String getInfo(){
            return "Info for Merch:" + this.id + "\n" +
                    "Name:      " + this.name + "\n" +
                    "Catergory: " + this.category + "\n" +
                    "Address:\n" + 
                    "Street Number: " + this.address[0] + "\n" +
                    "Street Name:   " + this.address[1] + "\n" +
                    "City:          " + this.address[2] + "\n" + 
                    "State:         " + this.address[3] + "\n" + 
                    "Zip:           " + this.address[4] + "\n" +
                    "Lat:"  + this.lat + "\n" +
                    "Lng:"  + this.lng;
        }
	
	
}
