//hard level
//attendance.jsp
<form action="saveAttendance" method="post">
  Student Name: <input type="text" name="name"><br>
  Date: <input type="date" name="date"><br>
  Present: <input type="checkbox" name="present"><br>
  <input type="submit" value="Submit">
</form>

//AttendanceServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String present = req.getParameter("present") != null ? "Yes" : "No";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/yourdb", "user", "pass");
            PreparedStatement ps = con.prepareStatement("INSERT INTO attendance(name, date, present) VALUES (?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, date);
            ps.setString(3, present);
            ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.setContentType("text/html");
        res.getWriter().println("<h2>Attendance Saved for " + name + "</h2>");
    }
}
