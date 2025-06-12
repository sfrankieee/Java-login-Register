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

public class Updated_By_Admin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String address=req.getParameter("address");
		String dept=req.getParameter("dept");
		PrintWriter pw=resp.getWriter();

		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root && password=root");
			PreparedStatement ps=con.prepareStatement("update emp set name=?,password=?,address=?,dept=? where email=?");
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, address);
			ps.setString(4, dept);
			ps.setString(5, email);
			int res=ps.executeUpdate();
			
			if(res!=0)
			{
//				RequestDispatcher rd=req.getRequestDispatcher("adminedit");
//				rd.forward(req, resp);
//				pw.println("<html><body><h1>Update is done</h1></body></html>");
				resp.sendRedirect("admins");
				
			}
			else
			{
				pw.println("<html><body><h1>some thing went wrong</h1></body></html>");

			}
		} catch (ClassNotFoundException | SQLException e) {
			pw.println("<html><body><h1>some exception occur</h1></body></html>");
			e.printStackTrace();
		}
		
	}
}
