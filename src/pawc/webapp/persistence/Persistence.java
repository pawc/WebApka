package pawc.webapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pawc.webapp.model.User;
import pawc.webapp.model.EntryModel;

public class Persistence{

    public static boolean isUserRegistered(User user) throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
            
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "select login from userpass where login='"+user.getLogin()+"';";
        ResultSet rs = stmt.executeQuery(query);

        boolean answer;

        if(rs.next()){
            answer=true;
        }
        else{
            answer=false;
        }

        rs.close();
        stmt.close();
        conn.close();                  
        return answer;
    }

    public static void newUser(User user) throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "INSERT INTO userpass VALUES ('"+user.getLogin()+"', '"+user.getHashedPass()+"');";
        stmt.executeUpdate(query);

        stmt.close();
        conn.close();
    }
    
    public static boolean login(User user) throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "SELECT * FROM userpass WHERE login='"+user.getLogin()+"';";
        ResultSet rs = stmt.executeQuery(query);
        
        boolean answer;

        if(!rs.next()) return false;
        if(user.getHashedPass().equals(rs.getString(2))){
            answer = true;
        }
        else{
            answer = false;
        }

        rs.close();
        stmt.close();
        conn.close();
        return answer;

    }    

    public static List<EntryModel> getAllEntries() throws SQLException, ClassNotFoundException{
        
        List<EntryModel> list = new ArrayList<EntryModel>();
        
        Class.forName("org.postgresql.Driver");
               
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "Select * from wall;";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            EntryModel entry = new EntryModel(rs.getString(1), rs.getString(2), rs.getString(3));
            list.add(entry);
        }      
        return list;
    }

}
