package com.alacriti.crowdFunding.util;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
  

public class MailUtil {
	public static final Logger log =Logger.getLogger(MailUtil.class);
	public static void sendMail(String invitedBy,String sendTo,String campaignTitle){  
	    
		  String host="mail.google.com";  
		  final String user="sravanthireddivari@gmail.com";//change accordingly  
		  final String password="sravssravs";//change accordingly  
		    
		  
		   //Get the session object  
		   Properties props = new Properties();  
		   props.put("mail.smtp.host",host);  
		   props.put("mail.smtp.auth", "true");  
		   props.put("mail.smtp.socketFactory.port", "465"); 
		     
		   Session session = Session.getDefaultInstance(props,  
		    new javax.mail.Authenticator() {  
		      protected PasswordAuthentication getPasswordAuthentication() {  
		    return new PasswordAuthentication(user,password);  
		      }  
		    });  
		  
		   //Compose the message  
		    try {  
		     MimeMessage message = new MimeMessage(session);  
		     message.setFrom(new InternetAddress(user));  
		     message.addRecipient(Message.RecipientType.TO,new InternetAddress(sendTo));  
		     message.setSubject("Invitation For Campaign \""+campaignTitle+"\"");  
		     message.setText("Hai,Please Login In to the crowd Funding http://192.168.35.58:8080/home and donate "+"\nInvited BY:: "+invitedBy);  
		       
		    //send the message  
		     Transport.send(message);  
		  
		     log.debug("message sent successfully...");  
		   
		     } catch (MessagingException e) {e.printStackTrace();}  
		 }    
	 
}
