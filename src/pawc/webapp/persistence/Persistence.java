package pawc.webapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persistence{

    public static boolean isUserRegistered(String user) throws ClassNotFoundException, SQLException{
            Class.forName("org.postgresql.Driver");
            
            Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
            Statement stmt = conn.createStatement();
            String query = "select login from userpass where login='"+user+"';";
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

    public static void newUser(String user, String hashedPass) throws ClassNotFoundException, SQLException{
            Class.forName("org.postgresql.Driver");
        
            Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO userpass VALUES ('"+user+"', '"+hashedPass+"');";
            stmt.executeUpdate(query);

            stmt.close();
            conn.close();
    }
    
}
