package project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Admin_Edit_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        // Get the parameters from the request
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String address = req.getParameter("address");
        String dept = req.getParameter("dept");

        // Start generating the HTML response
        pw.println("<html><body style='font-family: Arial, sans-serif;'>"
                + "<h1 align='center'>Update Profile Details</h1>"
                + "<div align='center'>"
                + "<form action='updatedbyadmin' method='post' style='width: 60%; background-color: #f9f9f9; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);'>"
                + "<table style='width: 100%; margin-top: 20px;'>"
                + "<tr><td><strong>Name:</strong></td><td><input type='text' name='name' value='" + name + "' style='width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;'></td></tr>"
                + "<tr><td><strong>Email:</strong></td><td><input type='text' readonly name='email' value='" + email + "' style='width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;'></td></tr>"
                + "<tr><td><strong>Password:</strong></td><td><input type='password' name='password' value='" + password + "' style='width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;'></td></tr>"
                + "<tr><td><strong>Dept:</strong></td><td><input type='text' name='dept' value='" + dept + "' style='width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;'></td></tr>"
                + "<tr><td><strong>Address:</strong></td><td><input type='text' name='address' value='" + address + "' style='width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px;'></td></tr>"
                + "<tr><td colspan='2' style='text-align: center;'>"
                + "<button type='submit' style='background-color: #007acc; color: white; padding: 8px 20px; border: none; border-radius: 4px; cursor: pointer;'>Update</button>"
                + "</td></tr>"
                + "</table>"
                + "</form>"
                + "</div>"
                + "</body></html>");
    }
}
