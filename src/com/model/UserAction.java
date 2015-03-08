package com.model;

import javax.servlet.http.HttpServletRequest;

import com.beans.TemplateBean;

import freemarker.template.SimpleHash;

public class UserAction {
	Login userlogin = new Login();
	TemplateBean templateBean = new TemplateBean();
	SimpleHash root = new SimpleHash();

	/**
	 * @param request
	 * @return
	 */
	public TemplateBean userLoginAction(HttpServletRequest request) {

		root = templateBean.getRoot();

		/* Get UserName and Password from the request parameter */
		String username = request.getParameter("UserName");
		String password = request.getParameter("PassWord");
		User user = new User(username);
		boolean checkValid = false;
		boolean checkValidSet = false;
		boolean checkProfileExist = false;
		boolean checkDelete = false;
		if (user.userId == -1) {
			checkValid = false;
			checkValidSet = true;
		}
		/*
		 * check if the Login is Valid
		 */
		if (!checkValidSet)
			checkValid = userlogin.checkUserValid(user, password);
		if (checkValid) {
			/* If Login is valid.Check if Profile Exists in the Database */
			checkProfileExist = new Profile(username).checkProfileExist();
			/* If Login is valid Check if User is deleted by the Admin */
			checkDelete = userlogin.checkProfileDeleted(user.getUserId());
			if (checkProfileExist) {
				root.put("caller", "Login");
				root.put("message", " ");
			} else {
				root.put("message", "Profile not Exist");
			}
			if (!checkDelete)
				root.put("message", "Your Profile has been Deleted");
			templateBean.setTemplateName("HomePage.ftl");
		} else {
			root.put("message", "Login Not Accepted");
			templateBean.setTemplateName("Login.ftl");
		}
		return templateBean;
	}

	public TemplateBean RegisterUserAction(HttpServletRequest request) {
		String username = request.getParameter("UserName");
		String password = request.getParameter("PassWord");
		String secureqn = request.getParameter("SecureQn");
		String secureans = request.getParameter("SecureAns");
		/* Insert New User into the dataBse */
		boolean result = userlogin.insertNewUser(username, password, secureqn,
				secureans);
		root = templateBean.getRoot();
		/* If Inserted Successfully */
		if (result) {
			root.put("caller", "Register");
			templateBean.setTemplateName("ProfileEditor.ftl");
		}
		/* If Error occurs while insertions. */
		else {
			root.put("message", "UserName already registered");
			templateBean.setTemplateName("Register.ftl");
		}
		return templateBean;
	}

	/*
	 * This action is triggered when user Create a new profile or edits an
	 * existing profile
	 */
	public TemplateBean createProfileAction(HttpServletRequest request) {
		root = templateBean.getRoot();
		Profile profile = new Profile(request);
		String message = " ";
		/* User wants to edit the profile */
		if (request.getParameter("caller").equals("Login")) {
			boolean result = profile.update();
			if (result)
				message = "Profile Updated";
		}
		/* User registers and wants to Create his profile */
		if (request.getParameter("caller").equals("Register")) {
			boolean result = profile.insert();
			if (result) {
				message = "Profile Created";
				root.put("profile", (UserProfile) profile);
				root.put("caller", "Login");
			}
		}
		root.put("message", message);
		// Update Userlogin table set Deleted =0
		profile.setDeletedFalse();
		templateBean.setTemplateName("HomePage.ftl");
		// insert the profile
		return templateBean;
	}

	/*
	 * This Action is triggered when the User wants to edit his Profile. The
	 * method retrieves the Current Profile from the DB and Presents it to the
	 * User
	 */
	public TemplateBean editProfileAction(HttpServletRequest request) {

		root = templateBean.getRoot();
		Profile profile = new Profile(request.getParameter("UserName"));
		profile.retrieve();
		root.put("profile", profile);
		root.put("caller", "Login");
		templateBean.setTemplateName("ProfileEditor.ftl");
		return templateBean;
	}

	/*
	 * This method is triggered when a User wishes to view a Profile of another
	 * User
	 * 
	 */
	public TemplateBean viewProfileAction(HttpServletRequest request) {
		root = templateBean.getRoot();
		String viewUser = request.getParameter("viewUser");
		Profile profile = new Profile(request.getParameter("UserName"));
		profile.retrieve();
		root.put("profile", (UserProfile) profile);
		root.put("viewUser", viewUser);
		templateBean.setTemplateName("UserProfile.ftl");
		return templateBean;
	}

	/*
	 * This method deletes the Profile of the user and Sets the deleted falg
	 */
	public TemplateBean deleteMyProfileAction(HttpServletRequest request) {
		root = templateBean.getRoot();
		String username = request.getParameter("UserName");
		Profile profile = new Profile(username);
		profile.deleteProfile();
		root.put("message", "Your Profile has been Deleted");
		templateBean.setTemplateName("HomePage.ftl");
		return templateBean;
	}

	/*
	 * This method is triggered when the Administrator deletes the Profile
	 */
	public TemplateBean deleteUserProfileAction(HttpServletRequest request) {
		root = templateBean.getRoot();
		String deleteUser = request.getParameter("deleteUser");
		Profile profile = new Profile(deleteUser);
		profile.deleteProfile();
		root.put("message", deleteUser + "'s Profile has been Deleted");
		root.put("list_of_names", new DBSearch().getAllUserNames());
		templateBean.setTemplateName("UserList.ftl");
		return templateBean;
	}

	/*
	 * This method is triggered by the SearchProfile form.Given the search
	 * category and the Search Key. This method returns the list of user's Full
	 * name matching the Search Criteria
	 */
	public TemplateBean searchProfileAction(HttpServletRequest request) {
		String searchCat = request.getParameter("SearchCat");
		String searchKey = request.getParameter("SearchKey");
		templateBean.getRoot().put("list_of_names",
				new DBSearch().searchUserProfiles(searchCat, searchKey));
		templateBean.setTemplateName("UserList.ftl");
		return templateBean;
	}

	/*
	 * This method is triggered when the User wants to create a new Profile.
	 * This methods calls the Create.ftl which is the Profile editor Form
	 */
	public TemplateBean createButtonAction(HttpServletRequest request) {
		templateBean.getRoot().put("caller", "Register");
		templateBean.setTemplateName("ProfileEditor.ftl");
		return templateBean;
	}

	/*
	 * This method is triggered when the SeachProfile link is click. It directs
	 * the User to the Search Page
	 */
	public TemplateBean searchButtonAction(HttpServletRequest request) {
		templateBean.setTemplateName("SearchProfile.ftl");
		return templateBean;
	}

	/*
	 * This method is invoked when the Sign Up link is clicked by the user who
	 * wants to register a new account.It directs the user to the registration
	 * page
	 */
	public TemplateBean signButtonAction(HttpServletRequest request) {
		templateBean.setRoot(null);
		templateBean.setTemplateName("Register.ftl");
		return templateBean;
	}

	/*
	 * This method is invoked when View All Profiles Link is clicked by the
	 * User.It redirects the User to the View all Profiles Page
	 * 
	 */
	public TemplateBean viewAllProfileAction(HttpServletRequest request) {
		/*
		 * The method returns a list of all User's Profiles which are not
		 * deleted
		 */
		templateBean.getRoot().put("list_of_names",
				new DBSearch().getAllUserNames());
		templateBean.setTemplateName("UserList.ftl");
		return templateBean;
	}

	public TemplateBean logOutUserAction(HttpServletRequest request) {
		/*
		 * This method invalidates the session and the user is logged out
		 */
		templateBean.getRoot().put("message", "You have logged out");
		templateBean.setTemplateName("Login.ftl");
		request.getSession().invalidate();
		return templateBean;
	}

	public TemplateBean goToHomeAction(HttpServletRequest request) {
		/*
		 * This methos directs the user to the HomePage at any point in the
		 * WebSite
		 */
		String username = request.getParameter("UserName");
		Profile profile = new Profile(request.getParameter("UserName"));
		User user = new User(username);
		boolean checkProfileExist = profile.checkProfileExist();
		boolean checkDelete = new Login().checkProfileDeleted(user.getUserId());
		if (!checkProfileExist)
			templateBean.getRoot().put("message", "Profile does not Exist");
		if (!checkDelete)
			templateBean.getRoot().put("message",
					"Your Profile has been Deleted");
		else
			templateBean.getRoot().put("message", " ");
		templateBean.setTemplateName("HomePage.ftl");
		return templateBean;
	}

	public TemplateBean sessionExpiredAction(HttpServletRequest request) {
		/*
		 * This method is invoked if Session Timeout occurs or if the User logs
		 * out
		 */
		templateBean.getRoot().put("message", "Your session has expired");
		templateBean.setTemplateName("Login.ftl");
		return templateBean;
	}

	public TemplateBean changePasswordAction(HttpServletRequest request) {
		/*
		 * this method changes the Old password with the new One and directs the
		 * user back to Login page
		 */
		User user = new User(request.getParameter("UserName"));
		String password = request.getParameter("PassWord");
		boolean result = new Login().changeUserPassword(user.getUserId(),
				password);
		if (result) {
			templateBean.getRoot().put("message",
					"Password has been  Reset. Login Again");
			templateBean.setTemplateName("Login.ftl");
		}
		return templateBean;

	}
}
