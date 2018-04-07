package com.mateusz.database;
import java.sql.*;

public class mDataBase {
	
	private Connection connection;
	
	public mDataBase() throws ClassNotFoundException,SQLException{
		this.connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?useSSL=false","root","password");
	}
	
	public ResultSet getTodoName(int todoID) throws SQLException {
		if(connection!=null) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("SELECT description FROM Todos WHERE _id="+todoID+";");
		}
		else
			return null;
	}
	
	public ResultSet getUserEmail(int userID) throws SQLException{
		if(connection!=null) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("SELECT email FROM Users WHERE _id="+userID+";");
		}
		else
			return null;
	}
	
	public boolean addUser(String name, String password, String email) throws SQLException{
		if(this.connection!=null) {
			String sql="INSERT INTO Users(name,password,email) VALUES(?,?,?)";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setString(1,name);
			p_statement.setString(2,password);
			p_statement.setString(3, email);
			p_statement.executeUpdate();
			return true;
		}
		else
			return false;
	}
	
	public void setDone(int id) throws SQLException {
		if(this.connection!=null) {
			String sql="UPDATE Todos SET done=? WHERE _id=?";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setBoolean(1, true);
			p_statement.setInt(2, id);
			p_statement.executeUpdate();
		}
	}
	
	public void setRemindDate(String date,int id) throws SQLException{
		if(this.connection!=null) {
			String sql="UPDATE Todos SET remind_date=? WHERE _id=?";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setString(1, date);
			p_statement.setInt(2, id);
			p_statement.executeUpdate();
		}
	}
	
	public void setUndone(int id) throws SQLException {
		if(this.connection!=null) {
			String sql="UPDATE Todos SET done=? WHERE _id=?";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setBoolean(1, false);
			p_statement.setInt(2, id);
			p_statement.executeUpdate();
		}
	}
	
	public boolean addTodo(String description, String category, int UserID) throws SQLException{
		if(this.connection!=null) {
			String sql="INSERT INTO Todos(description,category,UserID,done) VALUES(?,?,?,?)";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setString(1,description);
			p_statement.setString(2,category);
			p_statement.setInt(3,UserID);
			p_statement.setBoolean(4, false);
			p_statement.executeUpdate();
			return true;
		}
		else
			return false;
	}
	
	public boolean deleteTodo(int id) throws SQLException{
		if(this.connection!=null) {
			String sql="DELETE FROM Todos WHERE _id=?";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setInt(1,id);
			p_statement.executeUpdate();
			return true;
		}
		else
			return false;
	}
	
	public ResultSet getTodos(int UserID) throws SQLException{
		if(connection!=null) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("SELECT * FROM Todos WHERE UserID="+UserID+" ORDER BY category;");
		}
		else
			return null;
	}
	
	public ResultSet getUsers() throws SQLException{
		if(connection!=null) {
			Statement statement = connection.createStatement();
			return statement.executeQuery("SELECT * FROM Users;");
		}
		else
			return null;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() throws SQLException{
		if(this.connection!=null) {
			this.connection.close();
			this.connection=null;
		}
	}
	
	public void startConnection(String url) throws ClassNotFoundException,SQLException{
		String driverName = "org.sqlite.JDBC";
		Class.forName(driverName);
		this.connection=DriverManager.getConnection(url);
	}
}
