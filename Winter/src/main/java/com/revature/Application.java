package com.revature;


import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Bid;
import com.revature.model.SaleItem;
import com.revature.model.User;
import com.sun.mail.smtp.SMTPTransport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {
	
	final private boolean RUNEMAILS = false;
	final private boolean EXTRADETAILS = false;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}
	@Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
	    System.out.println("Good Morning, I Have Just Started Up!");
	    if(RUNEMAILS) {
		    Timer t = new Timer();	//a timer to keep track of time pasted 
		    t.scheduleAtFixedRate( 	//set-up for running a task at a fixed interval
		        new TimerTask()	
		        {		
		            public void run()	//set the task to run each interval
		            {
		            	requestList();
		            }
		        },
		        0,      	//run first occurrence immediately
		        3600000); 	//run again after every hour
	    }
	}

	public synchronized void requestList() {
		RestTemplate myRT = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		@SuppressWarnings("unchecked")
		List<Object> resp = (myRT.exchange("http://localhost:8080/SaleItem/getSendTo/false", HttpMethod.GET, entity, List.class).getBody());
		//create a translator from generic objects in hash map to sale item objects
		ObjectMapper mapper = new ObjectMapper();
		//create populatable lists
		List<SaleItem> toSend = new ArrayList<SaleItem>();
		List<User> sellers = new ArrayList<User>();
		List<User> buyers = new ArrayList<User>();
		List<String> sellerEmails = new ArrayList<String>();
		List<String> buyerEmails = new ArrayList<String>();
		List<String> didSell = new ArrayList<String>();
		//populate those lists
		for(int i = 0; i < resp.size(); i++) {
			//convert each object received into an sale item
			toSend.add(mapper.convertValue(resp.get(i), SaleItem.class));
			//add those objects to a sale item list
			SaleItem thisItem = toSend.get(i);
			//determine seller and add them to the seller and seller email list
			User seller = thisItem.getSeller();
			sellers.add(seller);
			String sellerEmail = seller.getEmail();
			sellerEmails.add(sellerEmail);
			//determine the buyer and add them to the buyer and buyer email list
			Bid winningBid = thisItem.getCurrentBid();
			User buyer = winningBid.getBidder();
			buyers.add(buyer);
			String buyerEmail = buyer.getEmail();
			buyerEmails.add(buyerEmail);
			//determine if the item sold or not
			System.out.println(sellerEmail + " + " + buyerEmail);
			if(sellerEmail.equals(buyerEmail)) {
				didSell.add("Not Sold");
			}
			else {
				didSell.add("Sold");
			}
		}
		if(EXTRADETAILS) {  //this is the changeable to see lists of... sellers, buyers, and if the sellers item sold. //--------------------------------------------
			System.out.println("Seller Emails");
			System.out.println(sellerEmails);
			System.out.println("Buyer Emails");
			System.out.println(buyerEmails);
			System.out.println("Sold List");
			System.out.println(didSell);
		}
		System.out.println("Compiled user lists, ready to send emails to appropriate people");
		//Send out emails based upon if the item sold for each sale item
		for(int i = 0; i < didSell.size(); i++) {
			//set up email prerequisites 
			Properties props = System.getProperties();
	    	props.put("mail.transport.protocol", "smtp");
	    	props.put("mail.smtp.port", 587); 
	    	props.put("mail.smtp.starttls.enable", "true");
	    	props.put("mail.smtp.auth", "true");
	    	Session session = Session.getInstance(props);
	    	System.out.println("Generated basis for email");
	    	//---------New Transport Object To Control Sending the emails-----------------
	    	SMTPTransport transport = new SMTPTransport(session, null);
	    	SMTPTransport transport2 = new SMTPTransport(session, null);
	        try {
	        	//Connect to AWS SES
	        	transport.connect("email-smtp.us-east-1.amazonaws.com", System.getenv("SESusername"), System.getenv("SESpassword"));
	        	if(didSell.get(i).equals("Sold"))transport2.connect("email-smtp.us-east-1.amazonaws.com", "AKIATVESFNUF6VNYTO52", "BPP4A0HHnnzNO/WKG151nO3up19/NbfdqwxX0VZUBf4z");
				//if item sold
				if(didSell.get(i).equals("Sold")) {
					//-------------Message to Seller----------------------------
					//set up recipients
					String from = sellerEmails.get(i);
					String to = buyerEmails.get(i);
			        
			        // Create a default MimeMessage object.
			        MimeMessage message = new MimeMessage(session);
	
			        // Set From: header field of the header.
			        message.setFrom(new InternetAddress(from));
	
			        // Set To: header field of the header.
			        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
			        // Set Subject: header field
			        message.setSubject("Your Item Has Finished Selling");
			        
			        // Generate the message
		        	StringBuilder body = new StringBuilder();
			        	body.append("Hello " + sellers.get(i).getName());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("This is an automated message from Ubuy! We are happy to inform you your item has been sold to " + buyers.get(i).getName());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("The sold item is " + toSend.get(i).getTitle());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("With the description of " + toSend.get(i).getDescription());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("It sold for $" + toSend.get(i).getCurrentBid().getCurrentBidPrice());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("Please Contact " + buyers.get(i).getName() + " at the following email to arrange payment and transferance of the item.");
			        	body.append(System.getProperty("line.separator"));
			        	body.append(buyers.get(i).getEmail());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("Thank you for using Ubuy, and have a great life!");
		        	//Translate the string builder into a string
			        String myText = body.toString();
		        	// Now set the actual message
			        message.setText(myText);
			        
			        System.out.println("Seller Email Ready To Send");
			        if(EXTRADETAILS)System.out.println(myText); 
			        // Send message
			        transport.sendMessage(message, message.getAllRecipients());

			        System.out.println("Sent message successfully....");
			        
			        //-------------Message to Buyer----------------------------
			        //set up recipients
					String from2 = buyerEmails.get(i);
					String to2 = sellerEmails.get(i);
			        
			        // Create a default MimeMessage object.
			        MimeMessage message2 = new MimeMessage(session);
	
			        // Set From: header field of the header.
			        message2.setFrom(new InternetAddress(from2));
	
			        // Set To: header field of the header.
			        message2.addRecipient(Message.RecipientType.TO, new InternetAddress(to2));
	
			        // Set Subject: header field
			        message.setSubject("You Have Won An Auction");
		        	// Generate the message
		        	StringBuilder body2 = new StringBuilder();
			        	body2.append("Hello " + buyers.get(i).getName());
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append("This is an automated message from Ubuy! We are happy to inform you you won the item sold by " + sellers.get(i).getName());
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append("The sold purchased is " + toSend.get(i).getTitle());
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append("With the description of " + toSend.get(i).getDescription());
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append("It you bought it for $" + toSend.get(i).getCurrentBid().getCurrentBidPrice());
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append("Please Contact " + sellers.get(i).getName() + " at the following email to arrange payment and transferance of the item.");
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append(sellers.get(i).getEmail());
			        	body2.append(System.getProperty("line.separator"));
			        	body2.append("Thank you for using Ubuy, and have a great life!");
		        	//Translate the string builder into a string
			        String myText2 = body2.toString();
		        	// Now set the actual message
			        message2.setText(myText2);
		        				        
			        System.out.println("Email Ready To Send To Buyer");
			        if(EXTRADETAILS)System.out.println(myText2);
			        // Send message
			        transport.sendMessage(message2, message2.getAllRecipients());
			        
			        System.out.println("Buyer Sent Message Successfully!");
			        
			        // Set item's alerted status in database to true			        
			        RestTemplate myRT2 = new RestTemplate();
					HttpHeaders headers2 = new HttpHeaders();
					headers2.setContentType(MediaType.APPLICATION_JSON);
					ObjectMapper obmp = new ObjectMapper();
					toSend.get(i).setAlerted(true);
					SaleItem convertMe = toSend.get(i);
					String sendObj = obmp.writeValueAsString(convertMe);
					HttpEntity <String> entity2 = new HttpEntity<String>(sendObj, headers2);
					SaleItem returnedSaleItem = myRT2.exchange("http://localhost:8080/SaleItem/serverUpdate/kjfndfnalsdflasdnflknq81483Noifhwkdafqrnkandsfknf", HttpMethod.POST, entity2, SaleItem.class).getBody();
					if(returnedSaleItem != null)System.out.println("Updated Database Alerted Status For Sale Item with ID: " + convertMe.getSaleId());
					else {
						System.out.println("FAILED TO UPDATE SALE ITEM WITH ID: " + convertMe.getSaleId());
					}
					if(EXTRADETAILS)System.out.println(returnedSaleItem);
			        
				}
				else {
					//-------------Message to Seller----------------------------
					//set up recipients
					String from = sellerEmails.get(i);
					String to = buyerEmails.get(i);
			        
			        // Create a default MimeMessage object.
			        MimeMessage message = new MimeMessage(session);
	
			        // Set From: header field of the header.
			        message.setFrom(new InternetAddress(from));
			        
			        // Set To: header field of the header.
			        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	
			        // Set Subject: header field.
			        message.setSubject("Your Item Has Not Been Sold");
			        
			        // Set Content Type Within Header.
			        //message.setHeader("Content-Type", );
			        
			        // Generate the message
		        	StringBuilder body = new StringBuilder();
			        	body.append("Hello " + sellers.get(i).getName());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("This is an automated message from Ubuy! We are sad to inform you your item has not been sold");
			        	body.append(System.getProperty("line.separator"));
			        	body.append("The unsold item is " + toSend.get(i).getTitle());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("With the description of " + toSend.get(i).getDescription());
			        	body.append(System.getProperty("line.separator"));
			        	body.append("Please feel free to try selling it again if you'd like. We wish you the best of luck.");
			        	body.append(System.getProperty("line.separator"));
			        	body.append("Thank you for using Ubuy, and have a great life!");
		        	//Translate the string builder into a string
			        String myText = body.toString();
		        	// Now set the actual message
			        message.setText(myText);
			        
			        System.out.println("Seller Email Ready To Send");
			        if(EXTRADETAILS)System.out.println(myText); 
			        // Send message
			        transport.sendMessage(message, message.getAllRecipients());

			        System.out.println("Sent message successfully....");
			        
			        // Set item's alerted status in database to true			        
			        RestTemplate myRT2 = new RestTemplate();
					HttpHeaders headers2 = new HttpHeaders();
					headers2.setContentType(MediaType.APPLICATION_JSON);
					ObjectMapper obmp = new ObjectMapper();
					toSend.get(i).setAlerted(true);
					SaleItem convertMe = toSend.get(i);
					String sendObj = obmp.writeValueAsString(convertMe);
					HttpEntity <String> entity2 = new HttpEntity<String>(sendObj, headers2);
					SaleItem returnedSaleItem = myRT2.exchange("http://localhost:8080/SaleItem/serverUpdate/kjfndfnalsdflasdnflknq81483Noifhwkdafqrnkandsfknf", HttpMethod.POST, entity2, SaleItem.class).getBody();
					if(returnedSaleItem != null)System.out.println("Updated Database Alerted Status For Sale Item with ID: " + convertMe.getSaleId());
					else {
						System.out.println("FAILED TO UPDATE SALE ITEM WITH ID: " + convertMe.getSaleId());
					}
					if(EXTRADETAILS)System.out.println(returnedSaleItem);
			        
				}
			}
	        catch(Exception ex) {
	        	System.out.println("CAUGHT EXCEPTION");
	            System.out.println("The emails may not have sent.");
	            System.out.println("Error message: " + ex.getMessage());
	            if(EXTRADETAILS) {
		            System.out.println(ex);
		            ex.printStackTrace();
	            }
			}
	        finally {
	        	try {
					transport.close();
				} 
	        	catch (MessagingException e) {
					System.out.println("Seller Transport Is Already Closed, No Need For Concern");
				}
	        	try {
	        		if(didSell.get(i).equals("Sold"))transport2.close();
				} 
	        	catch (MessagingException e) {
					System.out.println("Buyer Transport Is Already Closed, No Need For Concern");
				}
	        }
		}
	}
}

