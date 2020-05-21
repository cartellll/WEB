/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldObjectClass;

import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import javax.xml.bind.annotation.*;
/**
 *
 * @author baran
 */
@XmlRootElement(name="User")

public class Users {
    private int ID;
    private String name;
    private String login;
    private String password;
    private ArrayList <Points> points=new ArrayList();
    
    public int getID()
    {
        return ID;
    }
    
    @XmlAttribute
     public void setID(int ID)
    {
        this.ID=ID;
    }
    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

   @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
     
    @XmlAttribute
    public void setLogin(String login) {
        this.login = login;
    }
    
    @XmlTransient
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Points getPoint(int i)
    {
        try{
        return points.get(i);
        }
        catch(IndexOutOfBoundsException exp)
        {
            System.out.print("Выход за пределы массива пользователей");
        }
        return null;
    }
    
    public void addPoint(int i,Points cars)
    {
        points.add(i, cars);
    }
        
      public void removePoint(int i)
    {
        points.remove(i);
    }
      
     public int getPointSize()
     {
          return points.size();
     }
     
   
     public void setPoints( ArrayList <Points> points)
     {
        this.points=points;
     }
     
      public  ArrayList <Points> getPoints()
     {
        return points;
     }
}
