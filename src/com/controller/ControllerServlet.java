package com.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.TemplateBean;
import com.model.User;
import com.model.UserAction;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ControllerServlet extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	public ControllerServlet() {
		super();
	}

	private Configuration cfg;

	public void init() {
		cfg = new Configuration();
		cfg.setServletContextForTemplateLoading(getServletContext(),
				"WEB-INF/template");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/*
		 * caller holds the Button value which triggered Action
		 */
		String caller = "";
		try {
			caller = request.getParameter("SUBMIT");
		} catch (RuntimeException e2) {
			caller="ERROR";
			e2.printStackTrace();
		}
		Properties properties = new Properties();
		TemplateBean templateBean = new TemplateBean();
		/*
		 * Load the Properties File which maps the Button Values to the
		 * UserAction to be triggered
		 */
		FileInputStream fis = new FileInputStream(
				"C:/Documents and Settings/Administrator/workspace/OnlineProfile1/Properties.properties");
		properties.load(fis);
		fis.close();

		HttpSession session = request.getSession();

		/* Checks if session timeout has Occurred */
		if (session.isNew() && !caller.equals("Sign In")
				&& !caller.equals("SIGNUP")
				&& !caller.equals("CHANGE PASSWORD")) {

			caller = "SESSION EXPIRED";
			session.invalidate();
		}
		/*
		 * Get the UserAction to be triggered for the Corresponding button
		 * Click..Look Up the Properties Files
		 */
		String actionInvoked = properties.getProperty(caller);
		if(!actionInvoked.equals("error"))
		{
		UserAction userAction = new UserAction();
		Class c = userAction.getClass();
		Method methodToTrigger;
		/* The Argument to the Method is Of type HttpServletRequest */
		Class[] argTypes = new Class[] { HttpServletRequest.class };
		Object[] param = new Object[] { request };
		try {
			/*
			 * The Corresponding method is triggered and returns an Object of
			 * TemplateBean
			 */
			methodToTrigger = c.getMethod(actionInvoked, argTypes);
			templateBean = (TemplateBean) methodToTrigger.invoke(userAction,
					param);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		}
		else
		{
			templateBean.getRoot().put("error","errorOccurred");
			templateBean.setTemplateName("Error.html");
		}
		if (!caller.equals("SIGNUP")) {
			String username = request.getParameter("UserName");
			User user = new User(username);
			templateBean.getRoot().put("user", user);
		}
		/* Invoke the FTL template with the root */
		Template t = cfg.getTemplate(templateBean.getTemplateName());
		response.setContentType("text/html; charset=" + t.getEncoding());
		Writer out = response.getWriter();
		try {
			t.process(templateBean.getRoot(), out);
		} catch (TemplateException e) {
			throw new ServletException(
					"Error while processing FreeMarker template", e);
		}
	}
}