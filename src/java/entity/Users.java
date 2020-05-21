/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
/**
 *
 * @author baran
 */

@Entity
@Table(name = "USERS")
@XmlRootElement(name="User")


 @NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    , @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.ID = :ID")
    , @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name")
    , @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login")
    , @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")
    , @NamedQuery(name = "User.findByLoginPassword", query = "SELECT u FROM User u WHERE u.login = :login and u.password = :password")  })
    public class Users implements Serializable  {
    @Id
    @NotNull
    @Column(name = "ID")
    private int ID;
    @Size(max = 100)
    @Column(name = "NAME")
    private String name;
    @Size(max = 100)
    @Column(name = "LOGIN")
    private String login;
    @Size(max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @JoinTable(name = "USERPOINT", joinColumns = {
        @JoinColumn(name = "ID_USER", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_POINT", referencedColumnName = "ID")})
    @ManyToMany
    private List<Points> points;
    
    public Users()
    {
        
    }
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
     
     @XmlAttribute
     public void setPoints(List <Points> points)
     {
        this.points=points;
     }
     
      public  List <Points> getPoints()
     {
        return points;
     }
}
