package pawc.webapp.persistence;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@SessionScoped
public class Data {
    
    private Info info;
    private Userpass userpass;
    private Wall wall;
    private HibernateUtil helper;
    private Session session;
    private String message;
    private List<Userpass> userpassList;
    private List<Wall> wallList;
    
    public void newUser(String login, String hashedPass){
        session = helper.getSessionFactory().openSession();     
        Transaction t = session.beginTransaction();
        session.save(new Userpass(login, hashedPass));
        session.save(new Info(login, "", ""));
        t.commit();  
    }
    
    public void updateInfo(String login, String city, String email){
         session = helper.getSessionFactory().openSession();     
         Transaction t = session.beginTransaction();
         String hql = "FROM Info AS I where I.login='"+login+"'";
         Query query = session.createQuery(hql);
         Info temp = ((Info) query.list().get(0));
         temp.setCity(city);
         temp.setEmail(email);
         session.saveOrUpdate(temp);
         t.commit();
    }
    
    public Info getInfo(String login){
         session = helper.getSessionFactory().openSession();     
         Transaction t = session.beginTransaction();
         String hql = "FROM Info AS I where I.login='"+login+"'";
         Query query = session.createQuery(hql);
         return ((Info) query.list().get(0));
    }
    
    public boolean isUserRegistered(String login){
        session = helper.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Userpass";
        Query query = session.createQuery(hql);
        t.commit();
        userpassList = query.list();
        
        for(Userpass u : userpassList){
            if(login.equals(u.getLogin())) return true;
        }
        return false; 
    }
    
    public boolean login(String login, String hashedPass){
        session = helper.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Userpass U WHERE U.login='"+login+"'";
        Query query = session.createQuery(hql);
        t.commit();
        userpassList = query.list();
        if(userpassList.get(0).getHashedpass().equals(hashedPass)){
            return true;
        }
        else return false; 
    }
         
    public List<Wall> getAllEntries(){
        session = helper.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Wall ORDER BY id DESC";
        Query query = session.createQuery(hql);
        t.commit();
        wallList = query.list();
        return wallList;        
    }   
    
    public String newEntry(String login, String entry){
        session = helper.getSessionFactory().openSession();     
        Transaction t = session.beginTransaction();
        session.save(new Wall(login, entry));
        t.commit(); 
        return "page?faces-redirect=true&amp;includeViewParams=true";
    }
    
    public String getCity(String login){
        session = helper.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Info AS I where I.login='"+login+"'";
        Query query = session.createQuery(hql);
        t.commit();
        return ((Info) query.list().get(0)).getCity();
    }
    
        public String getEmail(String login){
        session = helper.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String hql = "FROM Info AS I where I.login='"+login+"'";
        Query query = session.createQuery(hql);
        t.commit();
        return ((Info) query.list().get(0)).getEmail();
    }
    
}
