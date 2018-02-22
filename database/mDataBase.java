package com.mateusz.database;
import java.sql.*;

public class mDataBase {
	private Connection connection;
	public mDataBase() throws ClassNotFoundException,SQLException{
		this.connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test","root","jaksiemasz");
	}
	
	public boolean addUser(String name, String password) throws SQLException{
		if(this.connection!=null) {
			String sql="INSERT INTO Users(name,password) VALUES(?,?)";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setString(1,name);
			p_statement.setString(2,password);
			p_statement.executeUpdate();
			return true;
		}
		else
			return false;
	}
	
	public boolean addTodo(String description, String category, int UserID) throws SQLException{
		if(this.connection!=null) {
			String sql="INSERT INTO Todos(description,category,UserID) VALUES(?,?,?)";
			PreparedStatement p_statement=connection.prepareStatement(sql);
			p_statement.setString(1,description);
			p_statement.setString(2,category);
			p_statement.setInt(3,UserID);
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
