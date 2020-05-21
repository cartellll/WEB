/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Entity.Users;
import auto.Database;
import entity.Cars;
import entity.Points;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless 
public class CustomerEJB {
    
    @PersistenceContext(unitName="web5PU")
    private EntityManager em;
    
    public Users validateUserLogin(String login, String password)
    {
        Users user = new Users();
        try
        {
            user = (Users) em.createNamedQuery("User.findByLoginPassword").setParameter("login",login).setParameter("password",password).getSingleResult();
              
            return user;
        }  
        catch(NoResultException exp)
        {
            user.setLogin(login);
            user.setPassword(password);
            user.setID(-1);
        }
       
    return user;
    
    }
    
    public void addCar(Cars car, int activePoint)
    {
    
     em.persist(car);
     car.setPoint(em.find(Points.class, activePoint));
     em.find(Points.class, activePoint).addElement(activePoint, car);
    
     
    }
    
    public String getCarsString()
    {
     String EMAIL_PATTERN = "";
     List<Cars>list = em.createNamedQuery("Car.findAll").getResultList();
     for(int i=0;i<list.size();i++)
        {
            EMAIL_PATTERN+=String.valueOf(list.get(i).getID());
            EMAIL_PATTERN+="|";
        }
     
     return EMAIL_PATTERN;
    }

    
   
    
}
