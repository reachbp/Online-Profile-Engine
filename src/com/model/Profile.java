package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

public class Profile extends UserProfile {
	DBConnection dbConnection = new DBConnection();

	public Profile(HttpServletRequest req) {
		super(req);
	}

	public Profile(String username) {
		super(username);
	}

	public boolean insert() {
		/*
		 * Inserts a New UserProfile
		 */
		String tableName = "profiles";
		String columnList = "(userid,FullName,Age,Gender,Profession,Books,Passions,Phone)";
		String values = "(" + this.user.userId + ",'" + this.fullName + "',"
				+ this.age + ",'" + this.gender + "','" + this.profession
				+ "','" + this.books + "','" + this.passions + "','"
				+ this.phone + "')";
		int ret = dbConnection.insertRow(tableName, columnList, values);
		dbConnection.closeConnection();
		if (ret == 1)
			return true;
		else
			return false;
	}

	public boolean update() {
		/*
		 * Updates an Existing profile
		 */
		String tableName = "profiles";
		String columnValues = "FullName='" + this.fullName + "'," + "Age="
				+ this.age + "," + "Gender='" + this.gender + "',"
				+ "Profession='" + this.profession + "'," + "Books='"
				+ this.books + "'," + "Passions='" + this.passions + "',"
				+ "Phone='" + this.phone + "' ";
		String whereClause = "userid=" + this.user.userId;
		int ret = dbConnection.updateRow(tableName, columnValues, whereClause);
		dbConnection.closeConnection();
		if (ret == 1)
			return true;
		else
			return false;
	}

	public void retrieve() {
		/*
		 * Gets the UserProfile from the Database and Sets it to the UserProfile
		 * Objects
		 */
		String tableName = "profiles";
		String query = " userid=" + this.user.userId;
		try {
			ResultSet rs = dbConnection.selectRows(tableName, query);
			while (rs.next()) {
				this.age = rs.getInt("Age");
				this.books = rs.getString("Books");
				this.fullName = rs.getString("FullName");
				this.gender = rs.getString("Gender");
				this.passions = rs.getString("Passions");
				this.profession = rs.getString("Profession");
				this.phone = rs.getString("Phone");
				
			}
			dbConnection.closeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public boolean deleteProfile() {
		/*
		 * Given a Userid this method deletes the Profile from the DB and Sets
		 * the deleted flag to 1
		 */
		String tableName = "profiles";
		String query = " userid=" + this.user.userId;

		int ret = dbConnection.deleteRow(tableName, query);
		if (ret == 1) {
			tableName = "userlogin";
			String columnValues = "deleted=1";
			String whereClause = "userid=" + this.user.userId;
			ret = dbConnection.updateRow(tableName, columnValues, whereClause);
			dbConnection.closeConnection();
			return true;
		} else
			return false;
	}

	public boolean checkProfileExist() {
		/*
		 * Returns true if Profile Exists for the given userid
		 */
		String tablename = "profiles";
		String query = " userid=" + this.user.userId;
		ResultSet rs = dbConnection.selectRows(tablename, query);
		try {
			while (rs.next()) {
				dbConnection.closeConnection();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean setDeletedFalse() {
		/*
		 * Sets the deleted Flag=0 to indicate that the Previously deleted User
		 * profile has been created again
		 */
		String tableName = "userlogin";
		String columnValues = "deleted=0";
		String whereClause = " userid=" + this.user.getUserId();
		int ret = dbConnection.updateRow(tableName, columnValues, whereClause);
		dbConnection.closeConnection();
		if (ret == 1)
			return true;
		return false;
	}
}
