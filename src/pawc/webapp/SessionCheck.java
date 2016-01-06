package pawc.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;

public class SessionCheck implements Filter{

public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        if(null==((String) request.getSession().getAttribute("SESSION_PARAM_CUSTOMERID")) || 
            ((String) request.getSession().getAttribute("SESSION_PARAM_CUSTOMERID")).equals("")){
            response.sendRedirect("index.jsp");
            return;
        }
        arg2.doFilter(request, response);
    }

    @Override
    public void destroy(){
    }

    @Override
    public void init(FilterConfig filterConfig){
    }

}