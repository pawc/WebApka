package pawc.webapp.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import pawc.webapp.persistence.Wall;

public class WallMapper implements RowMapper<Wall>{
   public Wall mapRow(ResultSet rs, int rowNum) throws SQLException {
			Wall wall = new Wall();
      wall.setId(rs.getInt("ID"));
      wall.setLogin(rs.getString("LOGIN"));
			wall.setMessage(rs.getString("MESSAGE"));
      return wall;
   }
}
