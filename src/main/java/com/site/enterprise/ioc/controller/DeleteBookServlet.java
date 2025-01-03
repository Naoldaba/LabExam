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


@WebServlet("/deleteurl")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private DBConnectionManager manager;
    private static final String query = "delete from books where id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }

        try (Connection con = manager.openConnection(); PreparedStatement ps = con.prepareStatement(query);) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();
            if (count == 1) {
                pw.println("<h2>Record is Deleted Successfully</h2>");
            } else {
                pw.println("<h2>Record is not deleted Successfully</h2>");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}