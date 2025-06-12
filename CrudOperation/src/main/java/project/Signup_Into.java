package project;

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

public class Signup_Into extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		Long phone=Long.parseLong(req.getParameter("phone"));
		String address=req.getParameter("address");
		String dept=req.getParameter("dept");
		int sal=Integer.parseInt(req.getParameter("salary"));
		String gender=req.getParameter("gender");
		
		PrintWriter pw=resp.getWriter();
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root && password=root");
			PreparedStatement ps=con.prepareStatement("insert into emp values (?,?,?,?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setLong(4, phone);
			ps.setString(5, address);
			ps.setString(6, dept);
			ps.setInt(7, sal);
			ps.setString(8, gender);
			ps.executeUpdate();
			
			pw.println("<html><body><h1>Signup successfully Done</h1></body></html>");
			RequestDispatcher rd=req.getRequestDispatcher("signin.html");
			rd.forward(req, resp);
			
		} catch (ClassNotFoundException | SQLException e) {
			pw.println("<html><body><h1 color='red'>Something went wrong</h1></body></html>");

		}
		
		
		
	}

}
