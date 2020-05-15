/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;
import ObjectClass.Car;
import ObjectClass.Point;
import java.sql.*;
import ObjectClass.User;
import javax.xml.bind.*;


/**
 *
 * @author baran
 */
public class Auto {

    public static final String UNIC_BINDING_NAME = "sort";
    
    public static void main(String[] args) throws JAXBException{
        
   
           try {
               
            
            Point point1=new Point(6);
            point1.inputList("Point1");
            Database base=new Database();
            base.Init();
          //  base.createModel();//создание модели БД
            
            //User user1=base.getUserPoints(1);
           // System.out.println(user1.getPoint(0).getElement(4).getID()); //вывод из базы пользоваетля 1
            
            Car car=new Car(49,"ss","aa",20,2);
            base.addCar(car);//добавление машины в базу
            

            base.deleteCar(new Car(49,"ss","aa",20,2));//удаление машины
           
            System.out.println(base.getCheapCar().get(0).getID());//вывыод самой дешёвой машины
            
            //User use7=new User(5,"Jula","cartel","1111");
            System.out.println(base.existUser("cartel","1111"));// проверка существования пользователя
            
           // User user=base.getUserPoints(1);
           // System.out.println(base.getUserLogin("cartel").getName());// вывод пользователя с точками
            User user4=new User();
            user4.setID(1);
            user4.setName("Ivan");
            user4.setLogin("cartel");
            user4.setPassword("11s1");
            
            JAXBContext context=JAXBContext.newInstance(User.class);
            Marshaller marsh=context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marsh.marshal(base.getUserPoints(user4), System.out);
            
           } 
           catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

     
    }
    
}
