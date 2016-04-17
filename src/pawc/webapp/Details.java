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
        String email = list.get(1);
        out.println("<p align=center><img src=http://kritsit.ddns.net:81/"+name+".jpg height=50 width=50>");
        out.println("<form action=UpdateCity method=post><p align=center><input type=text size=10 name=city value='"+city+"' /><input type=submit value='Update City' /></form>");
        out.println("<form action=UpdateEmail method=post><p align=center><input type=text size=20  name=email value='"+email+"' /><input type=submit value='Update e-mail' /></form>");
        out.println("<form action=Upload method=post enctype=multipart/form-data> <p align=center><input type=file name=file><p align=center><input type=submit value='Upload (max 10kB)'></form>");  
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
