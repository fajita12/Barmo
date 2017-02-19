package Mechanics;
import static Mechanics.Tester.globalHTTP;

import org.json.JSONObject;

import Mechanics.Purchase.Status;
import org.json.JSONArray;

public class Customer {
	
	private String customerId;
	private String accountId;
	private double accountBalance;
	private String firstName;
	private String lastName;
	private String[] address;
	

	public Customer(String customerId) {
		
		this.customerId = customerId;
		try {
			
                    JSONArray jArray = new JSONArray(globalHTTP.sendGet("customers/" + customerId + "/accounts"));
            JSONObject object = jArray.getJSONObject(0);
            this.accountId = object.getString("_id");
            this.accountBalance = object.getDouble("balance");
	    	
                    JSONObject obj = new JSONObject(globalHTTP.sendGet("customers/" + customerId));
                    this.firstName = obj.getString("first_name");
                    this.lastName = obj.getString("last_name");
                    address = new String[5];
                    JSONObject JSONaddress = obj.getJSONObject("address");
                    this.address[0] = JSONaddress.getString("street_number");
                    this.address[1] = JSONaddress.getString("street_name");
                    this.address[2] = JSONaddress.getString("city");
                    this.address[3] = JSONaddress.getString("state");
                    this.address[4] = JSONaddress.getString("zip");

                } catch(Exception e) {

        	customerId = null;
        	accountId = null;
        	accountBalance = 0;
        	firstName = null;
        	lastName = null;
        	address = null;
			
		}
                
		// post /customers
	}
	
	public int getCustomerByAccount(String customerId) {
		
		try {
			
			globalHTTP.sendGet("accounts/" + customerId + "/customer");
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// get /accounts/{id}/customer
	}
	
	public int getAllCustomers() {
		
		try {
			
			globalHTTP.sendGet("customers");
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// get /customers
	}
	
	public int getCustomerByID(String customerId) {
		
		try {
			
			globalHTTP.sendGet("customers/" + customerId);
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// get /customers/{id}
	}
	
	public int updateCustomer(String customerId, JSONObject body) {
		
		try {
			
			globalHTTP.sendPut("customers/" + customerId, body);
			return 0;
		} catch (Exception e) {
			
			return -1;
		}
		// put /customers/{id}
	}
        
    public JSONArray getPurchases() {
        
    	try {
    		return new JSONArray(globalHTTP.sendGet("accounts/" 
    					+ accountId + "/purchases"));
    	} catch (Exception e) {
    		
    		return null;
    	}
    }
    
    public double payTab(Purchase purchase, double amount) {
    	
    	double withstanding = purchase.getAmount() - amount;
    	
    	JSONObject obj = new JSONObject();
    	obj.put("amount", withstanding);
    	
    	if (withstanding == 0) {
    		obj.put("status", Status.COMPLETED.toString().toLowerCase());
    	}
    
    	if(purchase.updatePurchase(obj) == -1){
            return -1;
        }
    	
    	return withstanding;
    }
    
    public double addTip(Purchase purchase, double tip) {
    	
    	double total = purchase.getAmount() + tip;
    	
    	JSONObject obj = new JSONObject();
    	obj.put("amount", total);
    	obj.put("description", "Tip - $" + tip);
    	
    	purchase.updatePurchase(obj);
        
    	return total;
    }
    
    public String getAccountId() {
    	
    	return accountId;
    }
    
    public String getCustomerId() {
    	
    	return customerId;
    }
    
    public int fundAccount(double amount) {
    	
    	this.accountBalance = this.accountBalance + amount;
    	
    	JSONObject obj = new JSONObject();
    	obj.put("medium", "balance");
    	obj.put("transaction_data", "2017-01-01");
    	obj.put("amount", amount);
    	obj.put("description", "deposit");
    	
    	try {
    		globalHTTP.sendPost("accounts/" + accountId + "/deposits", obj);
    		return 0;
    	} catch (Exception e) {
    		return -1;
    	}
    	
    }
    
    public String getInfo() {
    	
    	return "Info for Customer:" + this.customerId + "\n" +
                "First name:      " + this.firstName + "\n" +
    			"Last name:		  " + this.lastName + "\n" +
                "Address:\n" + 
                "Street Number: " + this.address[0] + "\n" +
                "Street Name:   " + this.address[1] + "\n" +
                "City:          " + this.address[2] + "\n" + 
                "State:         " + this.address[3] + "\n" + 
                "Zip:           " + this.address[4] + "\n" +
                "Account Number:" + this.accountId;
        
    }
}
