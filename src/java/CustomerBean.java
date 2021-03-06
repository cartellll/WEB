/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import Entity.Users;
import entity.Cars;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

@ManagedBean(name = "CustomerBean", eager = true)
@SessionScoped
public class CustomerBean implements Serializable {
    
    @EJB
    private CustomerEJB customerEJB;
    private Users user = new Users();
    private Cars car = new Cars();
    private int activePoint;
    public int getActivePoint() {
        return activePoint;
    }

    public void setActivePoint(int activePoint) {
        this.activePoint = activePoint;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }
   
 
   
    public CustomerEJB getCustomerEJB() {
        return customerEJB;
    }

    public void setCustomerEJB(CustomerEJB customerEJB) {
        this.customerEJB = customerEJB;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
    public String sessUser()
    {
       
        this.user=customerEJB.validateUserLogin(user.getLogin(),user.getPassword());
        
        if(this.user.getID()==-1)
        {
            return "index?faces-redirect=true";
        }
        else
        {
            return "result?faces-redirect=true";
        }
    }
   
    public String setPoint(int point)
    {
        this.activePoint=point;
        return "insert?faces-redirect=true";
    }
  
       public void loadXML() throws  IOException
    {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
        ec.responseReset();
        ec.getSession(true);
        ec.setResponseContentType("text/xml");
        ec.setResponseHeader ("Content-Disposition", "attachment;filename = result.xml"); 
        
        OutputStream out = ec.getResponseOutputStream();
        
        try{
            System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        JAXBContext context=JAXBContext.newInstance(User.class);
        Marshaller marsh=context.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marsh.marshal(user, out);
        fc.responseComplete();
        }
        catch(JAXBException exp)
        {
            System.out.print("Ошибка маппинга");
        }
    }
    
    public String addCar()
    {
        customerEJB.addCar(car,activePoint);
        System.out.print(this.user.getPoint(0).getLengthArray());
        this.user=customerEJB.validateUserLogin(user.getLogin(),user.getPassword());
         System.out.print(this.user.getPoint(0).getLengthArray());
        return "result?faces-redirect=true";
    }
}
