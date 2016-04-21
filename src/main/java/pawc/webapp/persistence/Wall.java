package pawc.webapp.persistence;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Wall {
    
    private int id;
    private String login;
    private String message;

    public Wall(int id, String login, String message) {
        this.id = id;
        this.login = login;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public String getMessage() {
        return message;
    }
        
}
