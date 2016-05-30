package pawc.webapp.persistence;

public class Wall  implements java.io.Serializable {

     private Integer id;
     private String login;
     private String message;

    public Wall() {
    }

    public Wall(String login, String message) {
       this.login = login;
       this.message = message;
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
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }

}
