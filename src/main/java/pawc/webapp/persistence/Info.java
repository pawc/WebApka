package pawc.webapp.persistence;

public class Info  implements java.io.Serializable {

     private Integer id;
     private String login;
     private String city;
     private String email;

    public Info() {
    }

    public Info(String login, String city, String email) {
       this.login = login;
       this.city = city;
       this.email = email;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLogin() {
        return this.login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

}

