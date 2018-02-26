package com.mateusz.reminder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.mateusz.database.mDataBase;

public class Reminder implements Runnable {
	
	private String dateNow;
	private String dateRead;
	private boolean wrong=false;
	private int i=0;
	private mDataBase db;
	private int todoID;
	private int userID;
	private String todoName;
	private final static String user="your email";
	private final static String password="your password";
	private boolean running;
	private boolean checked=false;
	
	public Reminder(String date,int id,int userID,String todoName) {
		this.dateRead=date;
		this.todoID=id;
		this.userID=userID;
		this.todoName=todoName;
		this.running=true;
	}
	
	public void stopRunning() {
		this.running=false;
	}
	
	private void sendRemind() {
		try {
			db=new mDataBase();
			ResultSet rs=db.getUserEmail(userID);
			String emailto="example@example.com";
			if(rs.next())
				emailto=rs.getString(1);
			String from="your email";
			
			// Get system properties
		      Properties props = System.getProperties();

		    // Setup mail server
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");

		    // Get the default Session object.
		    Session session = Session.getInstance(props,
		    		new javax.mail.Authenticator() {
		    		protected PasswordAuthentication getPasswordAuthentication() {
		    			return new PasswordAuthentication(user, password);
		    		}
		    		});

		    try {
		        // Create a default MimeMessage object.
		        MimeMessage message = new MimeMessage(session);

		        // Set From: header field of the header.
		        message.setFrom(new InternetAddress(from));

		        // Set To: header field of the header.
		        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailto));

		        // Set Subject: header field
		        message.setSubject("HEY! You've got something to do!");

		        // Now set the actual message
		        message.setText("You planned: "+todoName);

		        // Send message
		        Transport.send(message);
		        System.out.println("Sent message successfully....");
		    } catch (MessagingException mex) {
		        mex.printStackTrace();
		    }
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		catch(ClassNotFoundException e1) {
			System.out.println(e1);
		}
		finally {
			try {
				db.closeConnection();
			}
			catch(SQLException e) {
				System.out.println(e);
			}
		}
	}

	@Override
	public void run() {
		while(running) {
			dateNow = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Calendar.getInstance().getTime());
			String [] timeN=dateNow.split("-");
			String [] timeR=dateRead.split("-");
			
	
			int [] timeRead=new int[5];
			int [] timeNow=new int[5];
			i=0;
			for(String a:timeR) {
				timeRead[i]=Integer.parseInt(a);
				i++;
			}
			i=0;
			for(String a:timeN) {
				timeNow[i]=Integer.parseInt(a);
				i++;
			}
			if(!checked) {
				for(i=0;i<5;i++) {
					if(timeRead[i]<timeNow[i])
					{
						wrong=true;
						break;
					}
				}
				if(wrong) {
					System.out.println("Wrong data given!");
					try {
						db=new mDataBase();
						db.setRemindDate(null, todoID);
					}
					catch(SQLException e) {
						System.out.println(e+"ReminderServlet");
					}
					catch(ClassNotFoundException e1) {
						System.out.println(e1);
					}
					finally {
						try {
							db.closeConnection();
						}
						catch(SQLException e) {
							System.out.println(e);
						}
					}
					break;
				}
				else
					checked=true;
			}
			System.out.println(this+" : "+"tic\n");
			i=0;
			while(timeRead[i]==timeNow[i]) {
				i++;
				if(i==5)
					break;
			}
			if(i==5) {//Send e-mail
				System.out.println("din din din din!!");
				sendRemind();
				try {
					db=new mDataBase();
					db.setRemindDate(null, todoID);
				}
				catch(SQLException e) {
					System.out.println(e+"ReminderServlet");
				}
				catch(ClassNotFoundException e1) {
					System.out.println(e1);
				}
				finally {
					try {
						db.closeConnection();
					}
					catch(SQLException e) {
						System.out.println(e);
					}
				}
				break;
			}
			try {
				Thread.sleep(30000);
			}
			catch(InterruptedException e) {
				System.out.println(e);
			}
		}
	}

}
