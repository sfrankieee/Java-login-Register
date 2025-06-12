package project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Edit_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        String address = (String) session.getAttribute("address");
        String dept = (String) session.getAttribute("dept");

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
                + "<h1>Update Profile Details</h1>"
                + "<div>"
                + "<form action='update' method='post'>"
                + "<table>"
                + "<tr>"
                + "<td>Name</td>"
                + "<td><input type='text' name='name' value='" + name + "'></td>"
                + "</tr>"
                + "<tr>"
                + "<td>Email</td>"
                + "<td><input type='text' readonly name='email' value='" + email + "'></td>"
                + "</tr>"
                + "<tr>"
                + "<td>Password</td>"
                + "<td><input type='password' name='password' value='" + password + "'></td>"
                + "</tr>"
                + "<tr>"
                + "<td>Dept</td>"
                + "<td><input type='text' name='dept' value='" + dept + "'></td>"
                + "</tr>"
                + "<tr>"
                + "<td>Address</td>"
                + "<td><input type='text' name='address' value='" + address + "'></td>"
                + "</tr>"
                + "<tr>"
                + "<td colspan='2' style='text-align:center;'><button type='submit'>Update</button></td>"
                + "</tr>"
                + "</table>"
                + "</form>"
                + "</div>"
                + "</body></html>");
    }
}
