package pawc.webapp.model;

public class User{

    private String login;
    private String hashedPass;

    public User(String login, String hashedPass){
        this.login = login;
        this.hashedPass = hashedPass;
    }
    
    public String getLogin(){
        return login;
    }

    public String getHashedPass(){
        return hashedPass;
    }

}
