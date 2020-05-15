/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;
import ObjectClass.*;
/**
 *
 * @author baran
 */
public class Test {
    
    Car car1,car2,car3,car4,car5,car6,car7,car8,car9,car10,car11,car12,car13,car14,car15,car16,
            car17,car18,car19,car20,car21,car22,car23,car24,car25,car26,car27,car28,car29,car30,
             car31,car32,car33,car34,car35,car36,car37,car38,car39,car40,car41,car42,car43,car44,car45,car46,
            car47,car48,car49,car50;
    
    Point point1,point2,point3,point4,point5,point6,point7,point8,point9,point10,point11,point12;
    User user1,user2,user3,user4,user5;
    public Test()
    {
        car1=new Car(1,"x6","bmw",20,1000000);
        car2=new Car(2,"A5","audi",2,100000);
        car3=new Car(3,"x4","bmw",54,120000);
        car4=new Car(4,"niva","shevrolet",15,330000);
        car5=new Car(5,"ria","kia",10,3800000);
        car6=new Car(6,"x3","bmw",27,1343000);
        car7=new Car(7,"x2","bmw",20,1000000);
        car8=new Car(8,"vesta","lada",12,120000);
        car9=new Car(9,"granta","lada",7,430000);
        car10=new Car(10,"kalina","lada",247,500000);
        car11=new Car(11,"A4","audi",23,1760000);
        car12=new Car(12,"A3","audi",42,320000);
        car13=new Car(13,"x1","bmw",2,3240000);
        car14=new Car(14,"2107","lada",234,100000);
        car15=new Car(15,"buisness","gazel",32,1000000);
        car16=new Car(16,"A2","audi",231,200000);
        car17=new Car(17,"kashkai","nissan",232,320000);
        car18=new Car(18,"x5","bmw",45,650000);
        car19=new Car(19,"2106","lada",435,10000);
        car20=new Car(20,"oktavia","shkoda",2,320000);
        car21=new Car(21,"2111","lada",21,150000);
        car22=new Car(22,"x6","bmw",43,100000);
        car23=new Car(23,"A5","audi",35,430000);
        car24=new Car(24,"x4","bmw",24,15340000);
        car25=new Car(25,"niva","shevrolet",123,543000);
        car26=new Car(26,"ria","kia",324,3540000);
        car27=new Car(27,"x3","bmw",321,135000);
        car28=new Car(28,"x2","bmw",353,105000);
        car29=new Car(29,"vesta","lada",12,145000);
        car30=new Car(30,"granta","lada",23,652000);
        car31=new Car(31,"kalina","lada",54,125000);
        car32=new Car(32,"A4","audi",65,174000);
        car33=new Car(33,"A3","audi",41,354000);
        car34=new Car(34,"x1","bmw",32,34000);
        car35=new Car(35,"2107","lada",234,450000);
        car36=new Car(36,"buisness","gazel",312,1650000);
        car37=new Car(37,"A2","audi",253,430000);
        car38=new Car(38,"kashkai","nissan",22,356000);
        car39=new Car(39,"x5","bmw",4,623000);
        car40=new Car(40,"2106","lada",45,150000);
        car41=new Car(41,"oktavia","shkoda",2,32000);
        car42=new Car(42,"2111","lada",21,159000);
        
        point1= new Point(1);
        point1.addElement(0, car1);
        point1.addElement(1, car2);
        point1.addElement(2, car3);
        point1.addElement(3, car4);
        point1.addElement(4, car5);
        point1.addElement(5, car6);
        point1.addElement(6, car7);
        
        point2=new Point(2);
        point2.addElement(0, car8);
        point2.addElement(1, car9);
        point2.addElement(2, car10);
        point2.addElement(3, car11);
        point2.addElement(4, car12);
        point2.addElement(5, car13);
        
        point3=new Point(3);
        point3.addElement(0, car14);
        point3.addElement(1, car15);
        point3.addElement(2, car16);
        point3.addElement(3, car17);
        point3.addElement(4, car18);
        point3.addElement(5, car19);
        point3.addElement(6, car20);
        point3.addElement(7, car21);
        
        point4=new Point(4);
        point4.addElement(0, car22);
        point4.addElement(1, car23);
        point4.addElement(2, car24);
        point4.addElement(3, car25);
        point4.addElement(4, car26);
        point4.addElement(5, car27);
        point4.addElement(6, car28);
        point4.addElement(7, car29);
        
        point5=new Point(5);
        point5.addElement(0, car30);
        point5.addElement(1, car31);
        point5.addElement(2, car32);
        point5.addElement(3, car33);
        point5.addElement(4, car34);
        point5.addElement(5, car35);
        point5.addElement(6, car36);
        point5.addElement(7, car37);
        
        point6=new Point(6);
        point6.addElement(0, car38);
        point6.addElement(1, car39);
        point6.addElement(2, car40);
        point6.addElement(3, car41);
        point6.addElement(4, car42);
        
        point1.outputList("Point1");
        point2.outputList("Point2");
        point3.outputList("Point3");
        point4.outputList("Point4");
        point5.outputList("Point5");
        point6.outputList("Point6");
       
           User user1,user2,user3,user4,user5;
        user1=new User();
        user1.setID(1);
        user1.setName("Ivan");
        user1.setLogin("cartel");
        user1.setPassword("111");
        
        user2=new User();
        user2.setID(2);
        user2.setName("Alex");
        user2.setLogin("bob");
        user2.setPassword("aa10");
        
        user3=new User();
        user3.setID(3);
        user3.setName("Segei");
        user3.setLogin("krest");
        user3.setPassword("111");
        
        user4=new User();
        user4.setID(4);
        user4.setName("Irina");
        user4.setLogin("sqrt");
        user4.setPassword("11s1");
        
        user5=new User();
        user5.setID(5);
        user5.setName("Julia");
        user5.setLogin("reka");
        user5.setPassword("sq1a1");
        
       
       user1.addPoint(0, point1);
       user1.addPoint(1, point2);
       
       user2.addPoint(0, point1);
       user2.addPoint(0, point3);
       
       user3.addPoint(0, point2);
       user3.addPoint(1, point6);
       user3.addPoint(1, point4);
       
       user4.addPoint(0, point5);
       user4.addPoint(1, point3);
       
       user4.addPoint(0, point2);
       user4.addPoint(1, point4);
       user4.addPoint(2, point6);
       
    }
    public void out()
    {
         
        point1.outputList("Point1");
        point2.inputList("Point1");
        point2.outputList("Point3");
    }
}
