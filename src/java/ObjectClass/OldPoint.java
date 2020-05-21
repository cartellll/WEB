/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ObjectClass;


import java.io.FileNotFoundException;
import java.io.*;
import java.text.ParseException;
import java.util.Iterator;
import java.util.TreeSet;
import java.io.IOException;
import java.util.*;
import java.io.Serializable;
import javax.xml.bind.annotation.*;
/**
 *
 * @author baran
 */
@XmlRootElement(name = "User")
@XmlAccessorType (XmlAccessType.FIELD)
public class OldPoint implements Serializable {
    private int ID;
    private ArrayList <Car> cars=new ArrayList();
   
    
   
    public OldPoint(int ID)
    {
       setID(ID);
    }
   
    
     public int getLengthArray()
    {
        return cars.size();
    }
    
    public void addElement(int i, Car car )
    {
      cars.add(i, car);
       car.setPoint(this.ID);
    }
    
    public void removeElment(int i)
    {
        cars.get(i).setPoint(0);
        cars.remove(i);
    }
    
   public void sort()
   {
       
       try{
      Set<Car> set = new LinkedHashSet<Car>(cars);
      cars.clear();
      cars.addAll(set);
         
       Collections.sort(cars);
       }
       catch(IndexOutOfBoundsException q)
       {
           System.out.println("пустой или некорректный");
       }
   }
   
   public Car getElement(int i)
   {
       try{
      return cars.get(i);
       }
       catch(IndexOutOfBoundsException e)
       {
           System.out.print("Выход за предел массив");
           return null;
       }
   }
   
   
    public void outputList(String Name)
       {
        PrintWriter Pw=null;
       
        try{
        Pw = new PrintWriter(Name);
        Pw.println(this.cars.size());
        for (int i = 0; i < cars.size(); i++) {
            Car.outputObject(cars.get(i), Pw);
        }
        }
        catch(FileNotFoundException exp)
        {
            System.out.print("При выводе не найден файл");
        }
        
        finally{
        Pw.flush();
        Pw.close();
        }
       }
       
       public void inputList(String Name)
       {
           try{
        BufferedReader br = new BufferedReader(new FileReader(Name));
            int size=Integer.parseInt(br.readLine());
     
            for (int i = 0; i < size; i++) {
               this.addElement(i,Car.inputObject(br));
            }
           }
           catch(FileNotFoundException exp)
           {
               System.out.print("При чтении не найден файл");
           }
           
           catch(IOException exp)
           {
               System.out.print("Ошибка при чтении ");
           }
           catch(NumberFormatException excp)
           {
               System.out.print("Нарушен формат файла");
           }
           
       }
       

       public void setID(int ID)
       {
         this.ID=ID;
       }
       
       public int getID()
       {
           return this.ID;
       }
       
     public void setCars( ArrayList <Car> cars)
     {
        this.cars=cars;
     }
     
      public  ArrayList <Car> getCars()
     {
        return cars;
     }
       
}
