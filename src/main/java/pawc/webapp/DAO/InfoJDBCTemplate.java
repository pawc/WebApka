package pawc.webapp.DAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pawc.webapp.persistence.Info;

public class InfoJDBCTemplate implements InfoDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
   
  @Override  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public Info getInfo(String login) {
    String SQL = "Select * from Info where login = ?";
    Info info = jdbcTemplateObject.queryForObject(SQL, new Object[]{login}, new InfoMapper());
    return info;
  }

  @Override
  public void insert(String login, String city, String email){
    String SQL = "Insert into Info (login, city, email) values (?, ?, ?)";
    jdbcTemplateObject.update(SQL, login, city, email);
    return;
  }
  
}
