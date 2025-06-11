package com.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // Getting the form data
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        PrintWriter pw = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root&password=root");

            PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM loginnn WHERE email=? AND pass=?");
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()&& rs.getInt(1) > 0) {
                RequestDispatcher rd = req.getRequestDispatcher("Welcome.html");
                rd.forward(req, res);
            } else {
                res.setContentType("text/html");
                pw.print("<h3 style='color:red'>Email id and password didn't match</h3>");
                RequestDispatcher rd = req.getRequestDispatcher("Login.html");
                rd.include(req, res);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

