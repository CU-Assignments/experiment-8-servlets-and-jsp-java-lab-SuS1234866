/*Medium Level: 
Create a servlet integrated with JDBC to display a list of employees from a database. Include a search form to fetch employee details by ID. */
//employee.html
<form method="get" action="employee">
  Enter Employee ID: <input type="text" name="id">
  <input type="submit" value="Search">
</form>

//EmployeeServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String id = req.getParameter("id");
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/yourdb", "user", "pass");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employees WHERE id = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                out.println("<h2>Employee Found:</h2>");
                out.println("ID: " + rs.getInt("id") + "<br>");
                out.println("Name: " + rs.getString("name") + "<br>");
                out.println("Position: " + rs.getString("position"));
            } else {
                out.println("<h2>No Employee Found</h2>");
            }

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}

