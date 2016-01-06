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
import javax.servlet.RequestDispatcher;
    
import pawc.webapp.persistence.Persistence;
import pawc.webapp.model.User;

import java.sql.SQLException;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PrintWriter out = null;
    
    public Register() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
    
        try{
            String name = request.getParameter("name");
            String pass = request.getParameter("password");
            String hashedPass = String.valueOf(pass.hashCode());
            User user = new User(name, hashedPass);
            if(Persistence.isUserRegistered(user)){
                RequestDispatcher rd = request.getRequestDispatcher("UserExists");
                rd.forward(request, response);
            }
            else{        
                Persistence.newUser(user);
                RequestDispatcher rd = request.getRequestDispatcher("SuccessPage");
                rd.forward(request, response);
            }   
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
        processRequest(request, response);
	}
	
}