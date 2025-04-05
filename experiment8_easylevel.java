/*Easy Level: 
Write a servlet to accept user credentials through an HTML form and display a personalized welcome message if the login is successful. */
//index.html (Login Form)
<form method="post" action="login">
  Username: <input type="text" name="username"><br>
  Password: <input type="password" name="password"><br>
  <input type="submit" value="Login">
</form>

//LoginServlet.java
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        
        PrintWriter out = res.getWriter();
        res.setContentType("text/html");

        if ("admin".equals(user) && "1234".equals(pass)) {
            out.println("<h2>Welcome, " + user + "!</h2>");
        } else {
            out.println("<h2>Login Failed</h2>");
        }
    }
}

//web.xml (Servlet Mapping)
<web-app>
  <servlet>
    <servlet-name>login</servlet-name>
    <servlet-class>LoginServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>login</servlet-name>
    <url-pattern>/login</url-pattern>
  </servlet-mapping>
</web-app>
