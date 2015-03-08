package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	Connection con;
	public Connection getConnection() {
		/*
		 * Establishes Connection with the MySql databse and returns an Object
		 * of type Connection
		 */

		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			String url = "jdbc:mysql://localhost/profile";
			Connection connection = java.sql.DriverManager.getConnection(url, "root",
					"root");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	public ResultSet selectRows(String tableName, String query) {
		try {
			/*
			 * Given a table name and a Sql Query clause ,this method performs
			 * a SELECT Query and returns the ResultSet from the DB.
			 */
			con=this.getConnection();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from " + tableName
					+ " where " + query);
			System.out
					.println("select * from " + tableName + " where " + query);
			
			return rs;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}

	public int insertRow(String tableName, String columnList, String values) {
		
		try {
			/*
			 * Given tablename and Columnnames and Values which have to inserted
			 * ,this method performs an INSERT Sql query . return An Integer
			 * representing number of rows affected
			 */
			con=this.getConnection();
			Statement st = con.createStatement();
			int ret = st.executeUpdate("insert into " + tableName + ""
					+ columnList + " values" + values);
			
			return ret;
		} catch (SQLException e) {

			e.printStackTrace();
		}finally
		{
		
		}
		return -1;
	}

	public int updateRow(String tableName, String columnValues,
			String whereClause) {
		
		try {
			/*
			 * This method Fires an Update SQL Query and return the number of
			 * rows affcted by this query
			 */
			con=this.getConnection();
			Statement st = con.createStatement();
			System.out.println("update profiles SET " + columnValues
					+ " where " + whereClause);
			int ret = st.executeUpdate("update " + tableName + " SET "
					+ columnValues + " where " + whereClause);
		
			return ret;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return -1;

	}

	public int deleteRow(String tableName, String query) {
	
		try {
			/*
			 * Fires a DELETE query to remove the Selected record from the DB.
			 * returns the number of rows affected
			 */
			con=this.getConnection();
			Statement st = con.createStatement();
			int ret = st.executeUpdate("delete from " + tableName + " where "
					+ query);
		
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	public void closeConnection()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
