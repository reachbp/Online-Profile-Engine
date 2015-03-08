package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/*Bean to store the UserName and UserId*/
public class User {
	String userName;
	int userId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public int getUserId() {
		return userId;

	}

	/* Given username..It gets the UserId from the database */
	private int getUseridFromDB(String username) {
		DBConnection dbc = new DBConnection();
		try {
			String query = " username='" + username + "'";
		
			ResultSet rs = dbc.selectRows("userlogin", query);
			while (rs.next()) {
				return rs.getInt("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public void setUserId(int userid) {
		this.userId = userid;
	}

	public User(String username) {
		super();
		this.userName = username;
		this.userId = getUseridFromDB(username);
	}

}
