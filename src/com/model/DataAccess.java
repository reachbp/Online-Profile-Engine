package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccess {
	DBConnection dbConnection = new DBConnection();
	ResultSet resultSet;

	public String getUserPassword(int userId, String password) {
		/*
		 * Get the Password Digest from the Database given the userid
		 */

		String query = " userid=" + userId;
		try {
			resultSet = dbConnection.selectRows("userlogin", query);
			while (resultSet.next()) {
				String getPassDB = resultSet.getString(3);
				
				return getPassDB;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int insertNewUser(String username, String passwordDigest,
			String securityQn, String securityAns) {
		/*
		 * Inserts a new record to the database. For every register , a new user
		 * is entered
		 */

		try {
			ResultSet rs = dbConnection.selectRows("userlogin", " username='"
					+ username + "'");
			while (rs.next()) {
				return -1;
			}
			int ret = dbConnection.insertRow("userlogin",
					"(username,password,deleted,securityqn,securityans)", "('"
							+ username + "','" + passwordDigest + "',0,'"
							+ securityQn + "','" + securityAns + "')");
			return ret;
		} catch (Exception e) {
		}
		return -1;
	}

	public int getIfDeletedUser(int userId) {
		/*
		 * returns an Integer 1 if the UserProfile is not deleted by the Admin
		 * or by the User itself
		 */
		try {
			String query = " userid=" + userId + " and deleted=0";
			resultSet = dbConnection.selectRows("userlogin", query);
			while (resultSet.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
}
