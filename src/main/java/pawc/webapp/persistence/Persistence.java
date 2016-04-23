package pawc.webapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Persistence{

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