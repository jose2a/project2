package com.revature.ctb.services;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessageServiceImpl {

	
	public void sendMessage() {
		
		String ACCOUNT_SID = "TWILIO_ACCOUNT_ID";
    	String AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
    	String TWILIO_NUMBER = "TWILIO_NUMBER";
    	
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
   
	
	}
}
