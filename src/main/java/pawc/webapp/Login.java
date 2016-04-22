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
import javax.faces.context.FacesContext;
import pawc.webapp.persistence.Data;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static PrintWriter out = null;
    
    public Login() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
            String name = request.getParameter("name");
            String pass = request.getParameter("password");
            String hashedPass = String.valueOf(pass.hashCode());
            Data data = new Data();
            if(data.login(name, hashedPass)){
                HttpSession session = request.getSession(true);
                request.setAttribute("login", name);
                response.sendRedirect("page.xhtml");
            }
            else{        
                response.sendRedirect("ErrorLogin");
            }   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
	}
	
}
