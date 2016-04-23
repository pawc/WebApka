package pawc.webapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import pawc.webapp.model.Bean;

@WebServlet(name = "Upload", urlPatterns = {"/Upload"})
public class Upload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();    
        
        Part filePart = request.getPart("file"); 
        String fileName = getFileName(filePart);
        InputStream fileContent = filePart.getInputStream();
        
        if(filePart.getSize()==0||filePart.getSize()>20480){
            out.println("Image too big (max 20kB");
            return;
        }
        String serverFileName = "";
        Bean bean = (Bean) request.getSession().getAttribute("atrybut");
        try{
            File file = new File("/opt/photos/"+bean.getLogin()+".jpg");
            serverFileName = file.getAbsolutePath();
            file.createNewFile();
            FileOutputStream output = new FileOutputStream(file);
            IOUtils.copy(fileContent, output);
        } catch(IOException e){
            out.println(e.toString());
            out.println("ok");
            return;
        }
        
    }
    
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
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
