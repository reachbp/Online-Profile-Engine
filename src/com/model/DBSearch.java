package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBSearch {
	public String getSearchValues(String searchKey, String searchCategory) {
		/*
		 * This method is invoked by SearchProfile form.Given a search Category
		 * and a search Value ,it returns a String containing Username which
		 * match the SearchCriteria seperated by comma's this method is invoked
		 * on Every KeyPress giving user suggestions
		 */
		String ret = " ";
		String query = "";
		if (searchCategory.equals("FullName"))
			query = " " + searchCategory + " like '" + searchKey + "%'";
		if (searchCategory.equals("Books"))
			query = " " + searchCategory + " like '%" + searchKey + "%'";
		DBConnection dbConnection = new DBConnection();
		ResultSet rs = dbConnection.selectRows("profiles", query);
		try {
			while (rs.next()) {
				ret += rs.getString(searchCategory) + "*";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		dbConnection.closeConnection();
		return ret;
	}

	public ArrayList searchUserProfiles(String searchCategory, String searchKey) {
		/*
		 * this method is invoked on the SUBMIT event .It return the list of all
		 * Users matching the Search Criteria as an arrayList
		 */
		DBConnection dbConnection = new DBConnection();
		ArrayList usernames = new ArrayList();
		String tableName = "profiles,userlogin";
		String query = " " + searchCategory + " like '" + searchKey
				+ "%' and profiles.userid=userlogin.userid ";
		try {
			ResultSet rs = dbConnection.selectRows(tableName, query);
			while (rs.next()) {
				usernames.add(rs.getString("userlogin.username"));
			}
			dbConnection.closeConnection();
			return usernames;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	public ArrayList getAllUserNames() {
		/*
		 * Method invoked on ViewAllProfiles Actions.
		 * It returns a List of All User's Profiles from the DB
		 */
		DBConnection dbConnection = new DBConnection();
		ArrayList userNames = new ArrayList();
		try {
			String query = " userlogin.deleted=0 and userlogin.userid=profiles.userid and userlogin.username!='admin'";
			String tableName = "profiles,userlogin";
			ResultSet rs = dbConnection.selectRows(tableName, query);
			while (rs.next()) {
				userNames.add(rs.getString("userlogin.username"));
			}
			dbConnection.closeConnection();
			return userNames;
		} catch (SQLException e) {
		}
		return null;
	}

}
