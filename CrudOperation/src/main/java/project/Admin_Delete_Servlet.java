//package project;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class Admin_Delete_Servlet extends HttpServlet{
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String email=req.getParameter("email");
//		System.out.println(email);
//
//		PrintWriter pw=resp.getWriter();
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?user=root && password=root");
//			PreparedStatement ps=con.prepareStatement("delete from signupdetails where email=?");
//			ps.setString(1, email);
//			int res=ps.executeUpdate();
//			if(res!=0)
//			{
////				RequestDispatcher rd=req.getRequestDispatcher("adminedit");
////				rd.forward(req, resp);
//				pw.println("<h1>Deleted</h1>");
////				resp.sendRedirect("adminedit");
//				
//			}
//			else
//			{
//				pw.println("<h1>some thing went wrong</h1>");
//			}
//			
//		} catch (ClassNotFoundException | SQLException e) {
//			pw.println("<h1>some exception occur</h1>");
//		}
//		
//	}
//}




package project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_Delete_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println(email);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("delete from emp where email=?");
            ps.setString(1, email);
            int res = ps.executeUpdate();
            
            if (res != 0) {
                resp.sendRedirect("admins");   
            } else {
                resp.getWriter().println("<h1>Something went wrong</h1>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            resp.getWriter().println("<h1>Some exception occurred</h1>");
            e.printStackTrace();
        }
    }
}

