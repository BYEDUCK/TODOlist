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
	private int i=0;
	private mDataBase db;
	private int todoID;
	private int userID;
	private String todoName;
	private final static String user="todoremindsender@gmail.com";
	private final static String password="password";
	private boolean running;

	public Reminder(String date,int id,int userID) {
		this.dateRead=date;
		this.todoID=id;
		this.userID=userID;
		this.running=true;
	}
	
	public void setTodoName(String todoName){
		this.todoName=todoName;
	}
	
	public boolean isDateCorrect() {
		String dateNow = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Calendar.getInstance().getTime());
		
		int[] timeRead = dateStringToIntArray(dateRead, "-");
		int[] timeNow = dateStringToIntArray(dateNow, "-");
		
		for(i=0;i<3;i++) {

			if(timeRead[i]<timeNow[i])
				return false;
			
		}
		
		if((60*timeRead[3]+timeRead[4])<(60*timeNow[3]+timeNow[4]))
			return false;
		
		return true;
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
			String from="todoremindsender@gmail.com";
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
				System.out.println(todoName);
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
	
	private int[] dateStringToIntArray(String date, String separator) {
		int [] result = new int[5];
		String [] sepDate=date.split(separator);
		int i=0;
		for(String temp:sepDate) {
			result[i]=Integer.parseInt(temp);
			i++;
		}
		return result;
	}
	
	private boolean itsTheTime(int[] date1, int[] date2) {
		
		int len=date1.length;
		
		if(len!=date2.length)
			return false;
		
		for(i=0;i<len;i++) {
			if(date1[i]!=date2[i])
				break;
		}
		
		if(i==len)
			return true;
		else
			return false;
	}

	@Override
	public void run() {
		while(running) {
			dateNow = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(Calendar.getInstance().getTime());

			int [] timeRead=dateStringToIntArray(dateRead, "-");
			int [] timeNow=dateStringToIntArray(dateNow, "-");
			
			
			if(itsTheTime(timeRead, timeNow)) {//Send e-mail
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
