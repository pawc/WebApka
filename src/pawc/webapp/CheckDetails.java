package pawc.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pawc.webapp.persistence.Persistence;

@WebServlet(name = "CheckDetails", urlPatterns = {"/CheckDetails"})
public class CheckDetails extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("login");
        if(name==null) response.sendRedirect("index.jsp");
        PrintWriter out = response.getWriter();
        try{
        String login = request.getParameter("info");
        List<String> list = Persistence.getInfo(login);
        out.println("<p align=center>user: "+login+"</p>");
        out.println("<p align=center>city: "+list.get(0)+"</p>");
        out.println("<p align=center>e-mail: "+list.get(1)+"</p>");
        out.println("<form action=Wall method=post><p align=center><input type=submit value='Back' /></form>");
        }
        catch(ClassNotFoundException | SQLException e){
            out.println("<html><p align=center>"+e.toString()+"</p><p align=center><a href=index.jsp>back</a></p></html>");
        }
        finally{
            out.close();
        }   

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
