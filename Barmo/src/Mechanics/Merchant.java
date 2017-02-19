package Mechanics;

import static Mechanics.Tester.globalHTTP;
import org.json.JSONObject;


public class Merchant {

	private String id;
	private String name;
	private String category;
	private String[] address;
	private String lat;
	private String lo;
	
	public Merchant(String id, String name, String category, String street_number, String street_name, String city, String state, String zip, String lat, String lo) {
		this.id = id;
                this.name = name;
                this.category = category;
                address = new String[5];
                this.address[0] = street_number;
                this.address[1] = street_name;
                this.address[2] = city;
                this.address[3] = state;
                this.address[4] = zip;
                this.lat = lat;
                this.lo = lo;
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
        
        public int sendTransaction(User user, Bill bill){
            
            return 0;
        }
        
        public int updateTransaction(User user, Bill currBill, Bill updateBill){
            
            return 0;
        }
        
        public int deleteTransaction(User user, Bill bill){
            
            return 0;
        }
	
	
}
