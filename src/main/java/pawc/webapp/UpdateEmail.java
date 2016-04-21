package pawc.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pawc.webapp.persistence.Persistence;

@WebServlet(name = "UpdateEmail", urlPatterns = {"/UpdateEmail"})
public class UpdateEmail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("login");
        if(name==null) response.sendRedirect("index.jsp");
        
        try{
            String email = request.getParameter("email");
            Persistence.updateEmail(name, email);
            response.sendRedirect("Details");
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
