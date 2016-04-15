/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

@WebServlet(name = "Details", urlPatterns = {"/Details"})
public class Details extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static PrintWriter out = null;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("login");
        if(name==null) response.sendRedirect("index.jsp");
        try{
        PrintWriter out = response.getWriter();
        out.println("<p align=center>Name: "+name);
        List<String> list = Persistence.getInfo(name);
        String city = list.get(0);
        String website = list.get(1);
        out.println("<form action=UpdateCity method=post><p align=center><input type=text name=city value="+city+" size=10 /><input type=submit value='Update City' /></form>");
        out.println("<form action=UpdateWebsite method=post><p align=center><input type=text name=website value="+website+" size=10 /><input type=submit value='Update website' /></form>");
        out.println("<form action=Wall method=post><p align=center><input type=submit value='Back' /></form>");
        }
        catch(ClassNotFoundException | SQLException e){
            out.println("<html><p align=center>"+e.toString()+"</p><p align=center><a href=index.jsp>powrót</a></p></html>");
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
