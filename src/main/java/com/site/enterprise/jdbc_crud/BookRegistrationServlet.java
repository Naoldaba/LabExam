package com.site.enterprise.jdbc_crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class BookRegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String query = "insert into books(bookname, bookedition, bookprice) values(?, ?, ?)";
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		String bookName = req.getParameter("title");
		String bookEdition = req.getParameter("author");
		float bookPrice = Float.parseFloat(req.getParameter("price"));
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		try {
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookregister?useSSL=false", "root", "Pass@202#");
	
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, bookName);
			ps.setString(2, bookEdition);
			ps.setFloat(3, bookPrice);
			
			int count = ps.executeUpdate();
			
			if(count == 1) {
				pw.println("<h2> Book registered successfully.</h2");
			}
			else {
				pw.println("<h2> Book Not registered successfully.</h2");
			}
		
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		
		pw.println("<a href='index.html'>Home</a>");
		pw.print("<br>");
		pw.println("<a href='bookList'>Book List</a>");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}
}