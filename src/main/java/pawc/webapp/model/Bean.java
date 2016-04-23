package pawc.webapp.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Bean {
    
    private String login;
    
    public Bean(){
        
    }

    public void setLogin(String login){
        this.login=login;
    }
    
    public String getLogin(){
        return login;
    }
    
}
