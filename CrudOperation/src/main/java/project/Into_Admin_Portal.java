package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Into_Admin_Portal extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PrintWriter pw = resp.getWriter();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root&password=root");

            if (email != null && password != null) {
                ps = con.prepareStatement("select * from emp where email=? and password=?");
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (!rs.next()) {
                    pw.println("<h1>Incorrect email or password</h1>");
                    return;
                }
            }

            ps = con.prepareStatement("select * from emp");
            rs = ps.executeQuery();
            
            // Start HTML for Admin Dashboard
            pw.println("<html><body style='font-family: Arial, sans-serif;'>"
                        + "<h1 align='center'>Admin Dashboard</h1>"
                        + "<div align='center'>"
                        + "<table border='1' cellspacing='0' style='width: 80%; margin-top: 20px;'>"
                        + "<tr style='background-color: #f2f2f2;'>"
                        + "<th>Name</th><th>Email</th><th>Password</th><th>Phone</th><th>Address</th><th>Dept</th><th>Salary</th><th>Gender</th><th>Edit</th><th>Delete</th>"
                        + "</tr>");
            
            while(rs.next()) {
            	if(!rs.getString(2).equals("admin@gmail.com")) {
            		
            	
                pw.println("<tr>"
                    + "<form action='adminedit' method='post'>"
                    + "<td><input name='name' type='text' value='" + rs.getString(1) + "'></td>"
                    + "<td><input name='email' type='text' value='" + rs.getString(2) + "'></td>"
                    + "<td><input name='password' type='text' value='" + rs.getString(3) + "'></td>"
                    + "<td><input name='phone' type='number' value='" + rs.getLong(4) + "'></td>"
                    + "<td><input name='address' type='text' value='" + rs.getString(5) + "'></td>"
                    + "<td><input name='dept' type='text' value='" + rs.getString(6) + "'></td>"
                    + "<td><input name='sal' type='number' value='" + rs.getInt(7) + "'></td>"
                    + "<td><input name='gender' type='text' value='" + rs.getString(8) + "'></td>"
                    + "<td><button type='submit' style='background-color: #007acc; color: white; padding: 5px 10px; border: none; cursor: pointer;'>Edit</button></td>"
                    + "</form>"
                    + "<td>"
                    + "<form action='admindelete' method='post'>"
                    + "<input type='hidden' name='email' value='" + rs.getString(2) + "'>"
                    + "<button type='submit' style='background-color: #dc3545; color: white; padding: 5px 10px; border: none; cursor: pointer;'>Delete</button>"
                    + "</form>"
                    + "</td>"
                    + "</tr>");
            }
            }

            // Close the table and HTML
            pw.println("</table></div></body></html>");
            
        } catch (ClassNotFoundException | SQLException e) {
            pw.println("<h1>Exception occurred: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);  // Call doPost from doGet
    }
}
