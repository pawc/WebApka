package pawc.webapp.persistence;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

@ManagedBean
@RequestScoped
public class Data {
    
    private HibernateUtil helper;
    private String message;
    
    public void newUser(String login, String hashedPass){
        Session session = null;
		Transaction t = null;
		try{
			session = helper.getSessionFactory().openSession();     
    	    t = session.beginTransaction();
        	session.save(new Userpass(login, hashedPass));
	        session.save(new Info(login, "", ""));
    	    t.commit();
		}
		catch(Exception e){
			t.rollback();
			e.printStackTrace();
		}  
		finally{
			session.close();
		}
    }
    
    public void updateInfo(String login, String city, String email){
		Session session = null;
		Transaction t = null;
        try{
		session = helper.getSessionFactory().openSession();     
        t = session.beginTransaction();
        String hql = "FROM Info AS I where I.login='"+login+"'";
        Query query = session.createQuery(hql);
        Info temp = ((Info) query.list().get(0));
        temp.setCity(city);
        temp.setEmail(email);
        session.saveOrUpdate(temp);
        t.commit();
		}
		catch(Exception e){
		t.rollback();
		e.printStackTrace();
		}
		finally{
		session.close();
		}
    }
    
    public Info getInfo(String login){
        Session session = null;
		Transaction t = null;
		Query q = null;
		try{
   		session = helper.getSessionFactory().openSession();     
        t = session.beginTransaction();
        String hql = "FROM Info AS I where I.login='"+login+"'";
        q = session.createQuery(hql);
		}
		catch(Exception e){
		t.rollback();
		e.printStackTrace();
		}
		finally{
		session.close();
        return ((Info) q.list().get(0));
		}
    }
    
    public boolean isUserRegistered(String login){
		Session session = null;
		Transaction t = null;
		Query q = null;
		List<Userpass> userpassList = null;
		try{
        session = helper.getSessionFactory().openSession();
        t = session.beginTransaction();
        String hql = "FROM Userpass";
        q = session.createQuery(hql);
        t.commit();
        userpassList = q.list();
        }
		catch(Exception e){
		t.rollback();
		e.printStackTrace();
		}
		finally{
        	for(Userpass u : userpassList){
            	if(login.equals(u.getLogin())) return true;
	        }
		session.close();
        return false;
		} 
    }
    
    public boolean login(String login, String hashedPass){
		Session session = null;
		Transaction t = null;
		Query q = null;
		List<Userpass> userpassList = null;
		try{
        session = helper.getSessionFactory().openSession();
        t = session.beginTransaction();
        String hql = "FROM Userpass U WHERE U.login='"+login+"'";
        q = session.createQuery(hql);
        t.commit();
        userpassList = q.list();
		}
		catch(Exception e){
			t.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
	        if(userpassList.get(0).getHashedpass().equals(hashedPass)){
    	        return true;
	        }
    	    else return false;
		} 
    }
         
    public List<Wall> getAllEntries(){
		Session session = null;
		Transaction t = null;
		Query q = null;
		List<Wall> wallList = null;
		try{
        session = helper.getSessionFactory().openSession();
       	t = session.beginTransaction();
        String hql = "FROM Wall ORDER BY id DESC";
        q = session.createQuery(hql);
        t.commit();
		wallList = q.list();
        }
		catch(Exception e){
			t.rollback();
			e.printStackTrace();
		}
		finally{
		session.close();
		return wallList;
		}        
    }   
    
    public String newEntry(String login, String entry){
		Session session = null;
		Transaction t = null;
		try{
		session = helper.getSessionFactory().openSession();     
        t = session.beginTransaction();
        session.save(new Wall(login, entry));
        t.commit(); 
        }
		catch(Exception e){
			t.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
			return "page?faces-redirect=true&amp;includeViewParams=true";
		}
    }
    
    public String getCity(String login){
		Session session = null;
		Transaction t =null;
		Query q = null;
        try{
		session = helper.getSessionFactory().openSession();
        t = session.beginTransaction();
        String hql = "FROM Info AS I where I.login='"+login+"'";
        q = session.createQuery(hql);
        t.commit();
		}
		catch(Exception e){
			t.rollback();
			e.printStackTrace();
		}
		finally{
		session.close();
        return ((Info) q.list().get(0)).getCity();
		}
    }
    
    public String getEmail(String login){
        Session session = null;
		Transaction t = null;
		Query q = null;
		try{
		session = helper.getSessionFactory().openSession();
        t = session.beginTransaction();
        String hql = "FROM Info AS I where I.login='"+login+"'";
        q = session.createQuery(hql);
        t.commit();
		}
		catch(Exception e){
			t.rollback();
			e.printStackTrace();	
		}
		finally{
		session.close();
        return ((Info) q.list().get(0)).getEmail();
		}
	}
    
}
