package pawc.webapp.persistence;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Info {
    
    private int id;
    private String login;
    private String city;
    private String email;

    public Info(int id, String login, String city, String email) {
        this.id = id;
        this.login = login;
        this.city = city;
        this.email = email;
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

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

}
