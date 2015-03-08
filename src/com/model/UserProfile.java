package com.model;

import javax.servlet.http.HttpServletRequest;



public class UserProfile {

	public UserProfile(HttpServletRequest req) {
		this.user = new User(req.getParameter("UserName"));
		this.age = Integer.parseInt(req.getParameter("age"));
		this.books = req.getParameter("books");
		this.fullName = req.getParameter("fullname");
		this.gender = req.getParameter("gender");
		this.passions = req.getParameter("passions");
		this.phone = req.getParameter("phone");
		this.profession = req.getParameter("profession");
	}

	public UserProfile(String username) {
		this.user = new User(username);

	}

	String fullName;
	int age;
	String gender;
	String profession;
	String books;
	String passions;
	String phone;
	User user;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		gender = gender;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		profession = profession;
	}

	public String getBooks() {
		return books;
	}

	public void setBooks(String books) {
		books = books;
	}

	public String getPassions() {
		return passions;
	}

	public void setPassions(String passions) {
		passions = passions;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
