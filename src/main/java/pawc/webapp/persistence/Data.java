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
        t.commit();  
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
        String hql = "FROM Wall";
        Query query = session.createQuery(hql);
        t.commit();
        wallList = query.list();
        return wallList;        
    }
    
}
