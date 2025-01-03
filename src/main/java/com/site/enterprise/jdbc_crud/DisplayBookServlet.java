package com.site.enterprise.jdbc_crud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookList")
public class DisplayBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String query = "select id, title, author, price from books";
	
	@Override 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		
		try {
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/bookregister?useSSL=false", "root", "Pass@202#");
	
			PreparedStatement ps = conn.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			pw.println("<a href='index.html'>Home</a>");
			pw.println("<table border = '1'>");
				pw.println("<tr>");
					pw.println("<th>Book Id</th>");
					pw.println("<th>Book Title</th>");
					pw.println("<th>Book Author</th>");
					pw.println("<th>Book Price</th>");
					pw.println("<th>Delete</th>");
				pw.println("</tr>");
			
			while(rs.next()) {
				pw.println("<tr>");
					pw.println("<td>" + rs.getInt(1) + "</td>");
					pw.println("<td>" + rs.getString(2) + "</td>");
					pw.println("<td>" + rs.getString(3) + "</td>");
					pw.println("<td>" + rs.getFloat(4) + "</td>");
					pw.println("<td><a href ='deleteurl?id=" + rs.getInt(1) +
					"'>delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
		
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		doGet(req, res);
	}
}