package pawc.webapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persistence{
   
    public static List<String> getInfo(String login) throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<String>();
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "select city,email from info where login='"+login+"';";
        ResultSet rs = stmt.executeQuery(query);
        rs.next();
        String city = rs.getString(1);
        String email = rs.getString(2);
        list.add(city);
        list.add(email);

        rs.close();
        stmt.close();
        conn.close();
        return list;
    }
    
    public static void updateCity(String login, String city) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String update = "update info set city='"+city+"' where login='"+login+"';";
        stmt.executeUpdate(update);
        stmt.close();
        conn.close();
    }
    
        public static void updateEmail(String login, String email) throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String update = "update info set email='"+email+"' where login='"+login+"';";
        stmt.executeUpdate(update);
        stmt.close();
        conn.close();
    }

    public static List<String> getRegisteredUsers() throws SQLException, ClassNotFoundException{
        List<String> list = new ArrayList<String>();
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "Select login from userpass";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
                list.add(rs.getString(1));
        }
        rs.close();
        stmt.close();
        conn.close();
        return list;
    }	 
    
}