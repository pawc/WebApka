package pawc.webapp.DAO;

import javax.sql.DataSource;
import pawc.webapp.persistence.Wall;

public interface WallDAO {
  public Wall getWall(int id);  
  public void setDataSource(DataSource ds);
  public void insert(String login, String message);
}
