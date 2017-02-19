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
                JSONObject address = new JSONObject(obj.getString("address"));
                this.address[0] = address.getString("street_number");
                this.address[1] = address.getString("street_name");
                this.address[2] = address.getString("city");
                this.address[3] = address.getString("state");
                this.address[4] = address.getString("zip");
                JSONObject geocode = new JSONObject(obj.getString("geocode"));
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
        
        public int sendTransaction(Customer user, Bill bill){
            
            return 0;
        }
        
        public int updateTransaction(Customer user, Bill currBill, Bill updateBill){
            
            return 0;
        }
        
        public int deleteTransaction(Customer user, Bill bill){
            
            return 0;
        }
	
	
}
