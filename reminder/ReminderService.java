package com.mateusz.reminder;

import java.sql.SQLException;
import java.util.HashMap;

import com.mateusz.database.mDataBase;

public class ReminderService {
	private HashMap reminders;
	private mDataBase db;
	
	public ReminderService() {
		this.reminders=new HashMap<Integer,Reminder>();
	}
	
	public void addReminder(int todoID,Reminder reminder) {
		this.reminders.put(todoID, reminder);
	}
	
	public void removeReminder(int todoID)
	{
		Reminder rem=(Reminder) this.reminders.get(todoID);
		rem.stopRunning();
		this.reminders.remove(todoID);
		try {
			db=new mDataBase();
			db.setRemindDate(null, todoID);
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
}
