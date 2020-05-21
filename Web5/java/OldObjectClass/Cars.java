/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OldObjectClass;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.*;
//import com.fasterxml.jackson.databind;



/**
 *
 * @author baran
 */

@XmlRootElement(name = "User")
@XmlAccessorType (XmlAccessType.FIELD)
public class Cars implements Serializable, Comparable<Cars> {
   private int ID;
   private String model;
   private String manufacturer;
   private int quantity;
   private int cost;
   private int point=0;
   
   public Cars()
   { 
       
   }
   
   public Cars(int ID,String model,String manufacturer,int quantity,int cost)
   {
       
       
       setID(ID);
       setModel(model);
       setManufacturer(manufacturer);
       setQuantity(quantity);
       setCost(cost);
       this.point=0;
     
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

Cars guest=(Cars) obj;

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
   
   public int compareTo(Cars car){
     
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
       
       public static void outputObject(Cars car,PrintWriter pW)
       {
          pW.println(car.ID);
          pW.println(car.model);
          pW.println(car.manufacturer);
          pW.println(car.quantity);
          pW.println(car.cost);
          pW.println(car.point);
       }
       
       public static Cars inputObject(BufferedReader br)
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
          
         return new Cars(ID,model,manufacturer,quantity,cost);   
        
       }
         
   public int getID()
          {
              return ID;
          }

   public void setID(int ID)
          {
             this.ID=ID;
          }
   
   public int getPoint()
   {
       return this.point;
   }
  
    public void setPoint(int point)
   {
       this.point=point;
   }
           

}
