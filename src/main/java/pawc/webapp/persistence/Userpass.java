
package pawc.webapp.persistence;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Userpass {
    private int id;
    private String login;
    private String hashedpass;

    public Userpass(int id, String login, String hashedpass) {
        this.id = id;
        this.login = login;
        this.hashedpass = hashedpass;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashedpass(String hashedpass) {
        this.hashedpass = hashedpass;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getHashedpass() {
        return hashedpass;
    }
    
}