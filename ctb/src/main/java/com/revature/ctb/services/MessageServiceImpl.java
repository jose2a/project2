package com.revature.ctb.services;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class MessageServiceImpl implements MessageService {
	
	private String ACCOUNT_SID = "TWILIO_ACCOUNT_ID";
	private String AUTH_TOKEN = "TWILIO_AUTH_TOKEN";
	private String TWILIO_NUMBER = "TWILIO_NUMBER";

	@Override
	public void sendMessage(String phoneNumber, String messageContent) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    	
    	Message message = Message.creator(
                new PhoneNumber(phoneNumber),
                new PhoneNumber(TWILIO_NUMBER),
                messageContent)
                .create();
		
	}
}