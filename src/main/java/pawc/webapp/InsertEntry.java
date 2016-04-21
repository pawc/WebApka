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
import pawc.webapp.model.EntryModel;

import java.sql.SQLException;

@WebServlet("/InsertEntry")
public class InsertEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PrintWriter out = null;
    
    public InsertEntry() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
    
        try{
            String message = (String) request.getParameter("message");
            if(message==null||message.equals("")){
                response.sendRedirect("Wall");
                return;
            }
            HttpSession session = request.getSession();
            String name = (String) session.getAttribute("login");
            EntryModel entry = new EntryModel(name, message);
            Persistence.addEntry(entry);
            response.sendRedirect("Wall");

        }
        catch(ClassNotFoundException | SQLException e){
            out.println("<html><p align=center>"+e.toString()+"</p><p align=center><a href=index.jsp>powrót</a></p></html>"); 
        }
        finally{
            out.close();
        }       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
}
