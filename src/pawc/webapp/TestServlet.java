package pawc.webapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pawc.webapp.persistence.Persistence;

import java.sql.SQLException;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PrintWriter out = null;
    
    public TestServlet() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        try{
            String name = request.getParameter("name");
            String pass = request.getParameter("password");
            Persistence.newUser(name, String.valueOf(pass.hashCode()));
            response.sendRedirect("SuccessPage.jsp");
        }
        catch(ClassNotFoundException | SQLException e){
            out.println("<html><p align=center>BŁĄD: "+e.toString()+"</p><p align=center><a href=index.jsp>powrót</a></p></html>"); 
        }
        finally{
            out.close();
        }       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
}
