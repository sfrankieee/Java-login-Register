package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signin_Into extends HttpServlet{
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String email=req.getParameter("email");
		 String password=req.getParameter("password");
		
		PrintWriter pw=resp.getWriter();
		HttpSession session=req.getSession();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root && password=root");
			PreparedStatement ps=con.prepareStatement("select * from emp where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				RequestDispatcher rd=req.getRequestDispatcher("homepage.html");
				rd.forward(req, resp);
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				
			}
			else
			{
				pw.println("<html><body><h1>incorrect email or password</h1></body></html>");
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			pw.println("<html><body><h1>some exception occur</h1></body></html>");

		}
		
	}

}
