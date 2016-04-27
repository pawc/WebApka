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
import pawc.webapp.model.User;

import java.sql.SQLException;
import pawc.webapp.persistence.Data;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static PrintWriter out = null;
    
    public Register() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        Data data = new Data();
    
        try{
            String name = request.getParameter("name");
            String pass = request.getParameter("password");
            if(name.length()<3 || pass.length()<3){
                out.println("<html><p align=center>Login and pass >= 3 characters</p><p align=center><a href=index.jsp>back</a></p></html>");
                return;
            }

            String hashedPass = String.valueOf(pass.hashCode());
            User user = new User(name, hashedPass);
            if(data.isUserRegistered(name)){
                response.sendRedirect("UserExists");
            }
            else{        
                data.newUser(name, hashedPass);
                response.sendRedirect("SuccessPage");
            }   
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
