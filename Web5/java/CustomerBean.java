/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ObjectClass.Car;
import ObjectClass.User;
import OldObjectClass.Cars;

import OldObjectClass.Points;
import OldObjectClass.Users;

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
    private User user = new User();
    private Car car = new Car();
    private int activePoint;
    public int getActivePoint() {
        return activePoint;
    }

    public void setActivePoint(int activePoint) {
        this.activePoint = activePoint;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
   
 
   
    public CustomerEJB getCustomerEJB() {
        return customerEJB;
    }

    public void setCustomerEJB(CustomerEJB customerEJB) {
        this.customerEJB = customerEJB;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
        Users OldUser = new Users();
        OldUser.setID(user.getID());
        OldUser.setLogin(user.getLogin());
        OldUser.setName(user.getName());
        OldUser.setPassword(user.getPassword());
        for(int i=0;i<user.getPointSize();i++)
        {
            Points point = new Points();
            for(int j=0;j<user.getPoint(i).getLengthArray();j++)
            {
               Cars car = new Cars();
               car.setID(user.getPoint(i).getCars().get(j).getID());
               car.setCost(user.getPoint(i).getCars().get(j).getCost());
               car.setManufacturer(user.getPoint(i).getCars().get(j).getManufacturer());
               car.setModel(user.getPoint(i).getCars().get(j).getModel());
               car.setPoint(user.getPoint(i).getID());
               point.addElement(j, car);
            }
            OldUser.addPoint(i, point);
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        
        ec.responseReset();
        ec.getSession(true);
        ec.setResponseContentType("text/xml");
        ec.setResponseHeader ("Content-Disposition", "attachment;filename = result.xml"); 
        
        OutputStream out = ec.getResponseOutputStream();
        
        try{
        JAXBContext context=JAXBContext.newInstance(Users.class);
        Marshaller marsh=context.createMarshaller();
        marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marsh.marshal(OldUser, out);
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
