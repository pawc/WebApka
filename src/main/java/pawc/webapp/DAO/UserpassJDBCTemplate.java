package pawc.webapp.DAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pawc.webapp.persistence.Userpass;

public class UserpassJDBCTemplate implements UserpassDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
   
  @Override  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public Userpass getUserpass(String login) {
    String SQL = "Select * from Userpass where login = ?";
    Userpass userpass = jdbcTemplateObject.queryForObject(SQL, new Object[]{login}, new UserpassMapper());
    return userpass;
  }

  @Override
  public void insert(String login, String hashedPass) {
    String SQL = "Insert into Userpass (login, hashedpass) values (?, ?)";
    jdbcTemplateObject.update(SQL, login, hashedPass);
    return;
  }
  
}
