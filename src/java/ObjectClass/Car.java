/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectClass;


import java.io.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
//import com.fasterxml.jackson.databind;



/**
 *
 * @author baran
 */

@Entity
@Table(name = "CARS")
@XmlRootElement(name = "User")
@XmlAccessorType (XmlAccessType.FIELD)


@NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
   
public class Car implements Serializable, Comparable<Car> {
   
   @Id
   @NotNull
   @Column(name = "ID") 
   private int ID;
   
   @Size(max = 100)
   @Column(name = "MODEL")
   private String model;
   
   @Size(max = 100)
   @Column(name = "MANUFACTURER")
   private String manufacturer;
   
   @Column(name = "QUANTITY")
   private int quantity;
   
   @Column(name = "COST")
   private int cost;
   
   @JoinColumn(name = "POINT", referencedColumnName = "ID")
   @ManyToOne
   private Point point;
   
   public Car()
   { 
       
   }
   
   public Car(int ID,String model,String manufacturer,int quantity,int cost)
   {
       
       
       setID(ID);
       setModel(model);
       setManufacturer(manufacturer);
       setQuantity(quantity);
       setCost(cost);
       
     
   }
   
   @Override
public boolean equals(Object obj) {
if (obj == this) 
{
        return true;         
}
if (obj == null || obj.getClass() != this.getClass()) {
            return false;
}

Car guest=(Car) obj;

return this.cost == guest.cost && this.manufacturer.equals(guest.manufacturer) && this.model.equals(guest.model)&&this.quantity==guest.quantity;
               
}

@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.cost;
		//result = prime * result + this.;
		return result;
	}
   
   public int compareTo(Car car){
     
       if(this.cost>car.cost)
            return 1;
       else
            return -1;
    }
   
   public String getModel()
   {
       return model;
   }
   
   public String getManufacturer()
   {
       return manufacturer;
   }
   
   public int getQuantity()
   {
       return quantity;
   }
   
   public int getCost()
   {
       
       return cost;
   }
   
   
   public void setModel(String model)
   {
       this.model=model;
   }
   
   
     public void setManufacturer(String manufacturer)
   {
       this.manufacturer= manufacturer;
   }
    
   
       public void setQuantity(int quantity)
   {
       if(quantity<1)
               {
                   try{
                   throw new IllegalArgumentException();
                   }
                   catch(IllegalArgumentException excp)
                   {
                       System.out.print("Неверно заданы значения");
                   }
                   }
       this.quantity=quantity;
   }
       
 
       public void setCost(int cost)
   {
       if(cost<0)
               {
                   try{
                   throw new IllegalArgumentException();
                   }
                   catch(IllegalArgumentException excp)
                   {
                       System.out.print("Неверно заданы значения");
                   }
                   }
       this.cost=cost;
   }
       
       public static void outputObject(Car car,PrintWriter pW)
       {
          pW.println(car.ID);
          pW.println(car.model);
          pW.println(car.manufacturer);
          pW.println(car.quantity);
          pW.println(car.cost);
          pW.println(car.point);
       }
       
       public static Car inputObject(BufferedReader br)
       {
        
        String model=null;
        String manufacturer=null;
        int quantity=0;
        int ID=0;
        int cost=0;
        int point=0;
          try{
         ID=Integer.parseInt(br.readLine());
         model=br.readLine();
         manufacturer = br.readLine();
         quantity = Integer.parseInt(br.readLine());
         cost=Integer.parseInt(br.readLine());
         point=Integer.parseInt(br.readLine());
           }
           catch(IOException ioe)
           {
               System.out.print("Ошибка при чтении из файла");
           }
          
         return new Car(ID,model,manufacturer,quantity,cost);   
        
       }
         
   public int getID()
          {
              return ID;
          }

   public void setID(int ID)
          {
             this.ID=ID;
          }
   
   public Point getPoint()
   {
       return this.point;
   }
  
    public void setPoint(Point point)
   {
       this.point=point;
   }
           

}
