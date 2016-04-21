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
        String query = "INSERT INTO userpass VALUES (1, '"+user.getLogin()+"', '"+user.getHashedPass()+"');";
        //String query2 = "INSERT INTO info VALUES ('"+user.getLogin()+"', ' ', ' ');";
        stmt.executeUpdate(query);
        //stmt.executeUpdate(query2);
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
        if(user.getHashedPass().equals(rs.getString(3))){
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

    public static List<EntryModel> getAllEntries() throws SQLException, ClassNotFoundException{
        List<EntryModel> list = new ArrayList<EntryModel>();
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "Select * from wall order by date desc;";
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            EntryModel entry = new EntryModel(rs.getString(1), rs.getString(2), rs.getString(3));
            list.add(entry);
        }      
	rs.close();
	stmt.close();
	conn.close();
    return list;
    }

    public static void addEntry(EntryModel entry) throws SQLException, ClassNotFoundException{

        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection("jdbc:postgresql://kritsit.ddns.net:5432/webapka", "webapka", "razdwatrzy");
        Statement stmt = conn.createStatement();
        String query = "insert into wall values('"+entry.getAuthor()+"','NOW()','"+entry.getMessage()+"');";
        stmt.executeUpdate(query);
	stmt.close();
	conn.close();        
    }

}
