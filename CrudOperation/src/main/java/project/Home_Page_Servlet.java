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
import javax.servlet.http.HttpSession;

public class Home_Page_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");

        PrintWriter pw = resp.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlett?user=root&&password=root");
            PreparedStatement ps = con.prepareStatement("select * from emp where email=? and password=?");
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            rs.next();

            pw.println("<html><head>"
                    + "<style>"
                    + "body {"
                    + "    font-family: Arial, sans-serif;"
                    + "    background: linear-gradient(135deg, #1f1c2c, #928dab);"
                    + "    margin: 0;"
                    + "    padding: 0;"
                    + "    display: flex;"
                    + "    flex-direction: column;"
                    + "    align-items: center;"
                    + "    justify-content: center;"
                    + "    height: 100vh;"
                    + "}"
                    + "h1 {"
                    + "    color: #fff;"
                    + "    text-align: center;"
                    + "}"
                    + "table {"
                    + "    width: 50%;"
                    + "    margin: 20px;"
                    + "    border-collapse: collapse;"
                    + "    background-color: white;"
                    + "    border-radius: 10px;"
                    + "    box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.1);"
                    + "}"
                    + "td {"
                    + "    padding: 10px;"
                    + "    text-align: left;"
                    + "    border: 1px solid #ddd;"
                    + "}"
                    + "button {"
                    + "    padding: 10px 20px;"
                    + "    font-size: 16px;"
                    + "    background-color: #007acc;"
                    + "    color: white;"
                    + "    border: none;"
                    + "    border-radius: 6px;"
                    + "    cursor: pointer;"
                    + "    transition: background-color 0.3s ease;"
                    + "}"
                    + "button:hover {"
                    + "    background-color: red;"
                    + "}"
                    + "</style>"
                    + "</head><body>"
                    + "<h1>Profile Details</h1>"
                    + "<div>"
                    + "<form>"
                    + "<table>"
                    + "<tr>"
                    + "<td>Name</td>"
                    + "<td><input type='text' readonly name='name' value='" + rs.getString(1) + "'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>Email</td>"
                    + "<td><input type='text' readonly name='email' value='" + email + "'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>Password</td>"
                    + "<td><input type='password' readonly name='password' value='" + password + "'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>Address</td>"
                    + "<td><input type='text' readonly name='address' value='" + rs.getString(5) + "'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>Salary</td>"
                    + "<td><input type='number' readonly name='salary' value='" + rs.getInt(7) + "'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>Dept</td>"
                    + "<td><input type='text' readonly name='dept' value='" + rs.getString(6) + "'></td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td>Gender</td>"
                    + "<td><input type='text' readonly name='gender' value='" + rs.getString(8) + "'></td>"
                    + "</tr>"
                    + "</table>"
                    + "</form>"
                    + "<table>"
                    + "<tr>"
                    + "<td>"
                    + "<form action='edit' method='post'>"
                    + "<button type='submit'>Edit</button>"
                    + "</form>"
                    + "</td>"
                    + "<td>"
                    + "<form action='delete' method='post'>"
                    + "<button type='submit'>Delete</button>"
                    + "</form>"
                    + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "</div>"
                    + "</body></html>");

            session.setAttribute("name", rs.getString(1));
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("address", rs.getString(5));
            session.setAttribute("dept", rs.getString(6));

        } catch (ClassNotFoundException | SQLException e) {
            pw.println("<html><body><h1>Some exception occurred</h1></body></html>");
            e.printStackTrace();
        }
    }
}
