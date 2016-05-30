package pawc.webapp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pawc.webapp.persistence.Info;

public class InfoMapper implements RowMapper<Info>{
   public Info mapRow(ResultSet rs, int rowNum) throws SQLException {
			Info info = new Info();
      info.setId(rs.getInt("ID"));
      info.setLogin(rs.getString("LOGIN"));
      info.setCity(rs.getString("CITY"));
			info.setEmail(rs.getString("EMAIL"));
      return info;
   }
}
