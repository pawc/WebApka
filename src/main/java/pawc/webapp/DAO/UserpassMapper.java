package pawc.webapp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pawc.webapp.persistence.Userpass;

public class UserpassMapper implements RowMapper<Userpass> {
   public Userpass mapRow(ResultSet rs, int rowNum) throws SQLException {
      Userpass userpass = new Userpass();
      userpass.setId(rs.getInt("ID"));
      userpass.setLogin(rs.getString("LOGIN"));
      userpass.setHashedpass(rs.getString("HASHEDPASS"));
      return userpass;
   }
}
