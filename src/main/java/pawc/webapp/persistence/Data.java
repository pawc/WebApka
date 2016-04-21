package pawc.webapp.persistence;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class Data {
    
    private Info info;
    private Userpass userpass;
    private Wall wall;
    private HibernateUtil helper;
    private Session session;
    private String message;
    
    public String getFirstEntry(){
        session = helper.getSessionFactory().openSession();
        session.beginTransaction();
        wall = (Wall) session.get(Wall.class, 1);
        this.message = wall.getMessage();
        return message;
    }
    
}
