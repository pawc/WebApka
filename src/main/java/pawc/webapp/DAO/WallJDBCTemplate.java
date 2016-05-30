package pawc.webapp.DAO;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pawc.webapp.persistence.Wall;

public class WallJDBCTemplate implements WallDAO{

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
   
  @Override  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }

  @Override
  public Wall getWall(int id){
    String SQL = "Select * from Wall where id = ?";
    Wall wall = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new WallMapper());
    return wall;
  }

  @Override
  public void insert(String login, String message){
    String SQL = "Insert into Wall (login, message) values (?, ?)";
    jdbcTemplateObject.update(SQL, login, message);
    return;
  }
  
}
