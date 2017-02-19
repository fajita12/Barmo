package Mechanics;
import static Mechanics.Tester.globalHTTP;

import org.json.JSONObject;



public class Purchase {
	
	public enum Status {
		PENDING, CANCELLED, COMPLETED, RECURRING
	}
	
	public enum Medium {
		BALANCE, REWARDS
	}
	
	private String purchaseId;
	private String type;
	private String merchantId;
	private String payerId;
	private String purchaseDate;
	private double amount;
	private Status status;
	private Medium medium;
	private String description;
	

	public Purchase(String purchaseId) {
		
		this.purchaseId = purchaseId;
		
		try {
		
			JSONObject obj = new JSONObject(globalHTTP.sendGet("purchases/" + purchaseId));
			this.type = obj.getString("type");
			this.merchantId = obj.getString("merchant_id");
			this.payerId = obj.getString("payer_id");
			this.purchaseDate = obj.getString("purchase_date");
			this.amount = obj.getDouble("amount");
			switch (obj.getString("status")) {
				case "pending": this.status = Status.PENDING;
				case "cancelled":this.status = Status.CANCELLED;
				case "completed":this.status = Status.COMPLETED;
				case "recurring":this.status = Status.RECURRING;
			}
			if (obj.getString("medium").equals("balance")) {
				this.medium = Medium.BALANCE;
			} else {
				this.medium = Medium.REWARDS;
			}
			this.description = obj.getString("description");
			
		} catch (Exception e) {
			type = null;
			merchantId = null;
			payerId = null;
			purchaseDate = null;
			amount = 0;
			status = null;
			medium = null;
			description = null;
			
		}
		// post /accounts/{id}/purchases
	}
        
        public Purchase(String customerId, String description, double amount, String merchantId){
            this.type = "merchant";
            this.merchantId = merchantId;
            this.payerId = customerId;
            this.purchaseDate = "1/1/2017";
            this.amount = amount;
            this.status = Status.PENDING;
            this.medium = Medium.BALANCE;
            this.description = description;
        }
	
	public int getPurchase(String purchaseId) {
		
		try {
			
			globalHTTP.sendGet("purchases/" + purchaseId);
			return 0;
			
		} catch (Exception e) {
			
			return -1;
		}
	}
	
	public int updatePurchase(String purchaseId, JSONObject body) {
		try {
			globalHTTP.sendPut("purchases/" + purchaseId, body);
			return 0;
			
		} catch (Exception e) {
			return -1;
		}
		
		// put /purchases/{purchaseId}
	}
	
	public int deletePurchase(String purchaseId) {
		
		try {
			globalHTTP.sendDelete("purchases/" + purchaseId);
			return 0;
		} catch (Exception e) {
			return -1;
		}
		
		// delete /purchases/{purchaseId}
	}
        
        public double getAmount(){
            return this.amount;
        }
        
        public String getDescription(){
            return this.description;
        }
        
        public String getMerchantId(){
            return this.merchantId;
        }
        
        public String getPurchaseId(){
            return this.purchaseId;
        }
        
        public JSONObject toJSON(){
            JSONObject obj = new JSONObject();
            obj.put("type", type);
            obj.put("merchant_id", merchantId);
            obj.put("payer_id",payerId);
            obj.put("purchase_date", purchaseDate);
            obj.put("amount",amount);
            obj.put("status", status.toString().toLowerCase());
            obj.put("medium", medium.toString().toLowerCase());
            obj.put("description", description);
            
            return(obj);
        }
        
}
