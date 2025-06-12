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
import javax.servlet.http.HttpSession;

public class Delete_Servlet_Page extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name=req.getParameter("name");
//		String email=req.getParameter("email");
		HttpSession session=req.getSession();
		String name=(String) session.getAttribute("name");
		String email=(String) session.getAttribute("email");

		PrintWriter pw=resp.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root && password=root");
			PreparedStatement ps=con.prepareStatement("delete from emp where name=? and email=?");
			ps.setString(1, name);
			ps.setString(2, email);
			int res=ps.executeUpdate();
			if(res!=0)
			{
				RequestDispatcher rd=req.getRequestDispatcher("index.html");
				rd.forward(req, resp);
			}
			else
			{
				pw.println("<h1>some thing went wrong</h1>");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			pw.println("<h1>some exception occur</h1>");
		}
		
		
	}

}
