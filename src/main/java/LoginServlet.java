import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if(username.equals("username") && password.equals("password")){
            session.setAttribute("user", true);
            Cookie loginCookie = new Cookie("user", username);
            //setting cookie to expire in 5 mins
            loginCookie.setMaxAge(5*60);
            response.addCookie(loginCookie);
            response.sendRedirect("/profile");
        }else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            try {
                rd.include(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
