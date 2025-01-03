package com.site.enterprise.ioc.controller;

import com.site.enterprise.util.DBConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;

@WebServlet("/register")
public class BookRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DBConnectionManager manager;
    
    @Autowired
    public BookRegistrationServlet(DBConnectionManager manager) {
    	this.manager = manager;
    }

    private static final String query = "INSERT INTO books (title,author,price) VALUES(?,?,?)";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String price = req.getParameter("price");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        try (Connection con = manager.openConnection(); PreparedStatement ps = con.prepareStatement(query);) {
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setString(3, price);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Record Is Registered Sucessfully</h2>");
            } else {
                pw.println("<h2>Record not Registered Sucessfully");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>" + se.getMessage() + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h2>");
        }
        pw.println("<a href='index.html'>Home</a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>Book List</a>");
    }
}

