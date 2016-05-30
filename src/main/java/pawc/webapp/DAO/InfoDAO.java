package pawc.webapp.DAO;

import javax.sql.DataSource;
import pawc.webapp.persistence.Info;

public interface InfoDAO {
  public Info getInfo(String login);  
  public void setDataSource(DataSource ds);
  public void insert(String login, String city, String email);
}
