package pawc.webapp.DAO;

import javax.sql.DataSource;
import pawc.webapp.persistence.Userpass;

public interface UserpassDAO {
  public Userpass getUserpass(String login);  
  public void setDataSource(DataSource ds);
  public void insert(String login, String hashedPass);
}
