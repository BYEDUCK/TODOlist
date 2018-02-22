package com.mateusz.login;

import com.mateusz.database.mDataBase;
import java.sql.*;

public class LoginService {
	
	private mDataBase db;

	public int isUserValid(String user, String password) {
		try {
			db=new mDataBase();
			ResultSet rs=db.getUsers();
			while(rs.next()) {
				if(user.equals(rs.getString("name"))) {
					if(password.equals(rs.getString("password"))) {
						return rs.getInt(1);
					}
					else {
						return -1;
					}
				}
			}
			return -1;
		}
		catch(SQLException e) {
			System.out.println(e+"LoginService");
			return -1;
		}
		catch(ClassNotFoundException e1) {
			System.out.println(e1);
			return -1;
		}
		finally {
			try {
				db.closeConnection();
			}
			catch(SQLException e) {
				System.out.println(e+"LoginService");
			}
		}
	}
	
}
