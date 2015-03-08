package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.DBConnection;
import com.model.DBSearch;


public class XmlController extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	public XmlController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
 /* Variable caller is used to hold the Button value which triggered Action
  * The Request is type XML
  */
		String caller = request.getParameter("SUBMIT");
		if (caller.equals("FORGOTXML")) {
			/* The action is triggered by the ForgotPassword.html
			 * to get the securityQn and securityAns for the correspondin Username
			 * 
			 */
			String username = request.getParameter("UserName");
			DBConnection dbc = new DBConnection();
			ResultSet rs = dbc.selectRows("userlogin", " username='" + username
					+ "'");
			int flag = 0;/*Flag is set if the Username is Valid*/
			String secureqn = "";
			try {
				while (rs.next()) {
					secureqn = rs.getString("securityqn");
					secureqn += "&" + rs.getString("securityans");
					flag = 1;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			if (flag == 1)
				out.print(secureqn);
			else/*Flag is not set..The username does not exist*/
				out.print("User Name does not exist");
			out.flush();
		}
		if (caller.equals("XML")) {
			/*This action is triggered by the SearchProfile form.
			 * The request is sent for every KeyPress of the User
			 * The method retrieves corresponding values for the KeyPress
			 */
			String key = request.getParameter("key");
			String cat = request.getParameter("cat");
			DBSearch dbs = new DBSearch();
			String ret = dbs.getSearchValues(key, cat);
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(ret);
			out.flush();
		}
	}
}