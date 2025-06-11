package com.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//@WebServlet("/Registration")
public class Registration extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=req.getParameter("uname");
		String email=req.getParameter("uemail");
		String pass=req.getParameter("upass");
		String phone=req.getParameter("uphone");
		PrintWriter pw = res.getWriter();

		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root&&password=root");
			java.sql.Statement stmt=con.createStatement();
			PreparedStatement ps = con.prepareStatement("INSERT INTO loginnn (name, email, pass, phone) VALUES (?, ?, ?, ?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, pass);
			ps.setString(4, phone);
			ps.executeUpdate();
			
				RequestDispatcher rd=req.getRequestDispatcher("login.html");
				rd.forward(req, res);
				
				
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
