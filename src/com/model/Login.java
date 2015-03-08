package com.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Login {
	DataAccess dataAccessObject = new DataAccess();

	public boolean checkUserValid(User user, String password) {
		/*
		 * Performs authentication of User. Gets the Password Digest from the
		 * Database. Forms a digest value for the Password given by the User.
		 * Return valid if Both the digest are same
		 */
		String passFromDB = dataAccessObject.getUserPassword(user.getUserId(), password);
		return passFromDB.equals(getDigestValue(password));
	}

	private String getDigestValue(String password) {
		try {
			/*
			 * Return an MD5 Hash digest for the input String The digest is
			 * encoded to Base 64 format
			 */
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] input = password.getBytes();
			md.update(input);
			byte[] raw = md.digest();
			BASE64Encoder encoder = new BASE64Encoder();
			String base64 = encoder.encode(raw);
			System.out.println("digest value " + base64);
			return base64;
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return null;

	}

	public boolean insertNewUser(String username, String password,
			String securityQn, String securityAns) {
		/*
		 * Fired by the RegisterUser Action Inserts a new user in the DB
		 */
		String passwordDigest = getDigestValue(password);
		int ret;
		ret = dataAccessObject.insertNewUser(username, passwordDigest, securityQn, securityAns);
		if (ret == 1)
			return true;
		else
			return false;
	}

	public boolean checkProfileDeleted(int userId) {
		/*
		 * Given a Userid .This method return false if the UserProfile is not
		 * deleted from the DataBase either by the the Administrator or by the
		 * User himself
		 */
		int ret;
		ret = dataAccessObject.getIfDeletedUser(userId);
		if (ret == 1)
			return true;
		else
			return false;
	}

	public boolean changeUserPassword(int userId, String password) {
		/*
		 * Updates the userpassword. It gets the digest for the New Password.and
		 * Updates the DB with the Digest
		 */
		DBConnection dbConnection = new DBConnection();
		int ret = dbConnection.updateRow("userlogin", "password='"
				+ getDigestValue(password) + "'", " userid=" + userId);
		if (ret == 1)
			return true;
		else
			return false;

	}
}
