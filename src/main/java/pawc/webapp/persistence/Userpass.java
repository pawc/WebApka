package pawc.webapp.persistence;

public class Userpass  implements java.io.Serializable {

     private Integer id;
     private String login;
     private String hashedpass;

    public Userpass() {
    }

    public Userpass(String login, String hashedpass) {
       this.login = login;
       this.hashedpass = hashedpass;
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
    public String getHashedpass() {
        return this.hashedpass;
    }
    
    public void setHashedpass(String hashedpass) {
        this.hashedpass = hashedpass;
    }

}
