/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import java.sql.*;
import java.util.Locale;
import ObjectClass.*;
import java.util.*;
/**
 *
 * @author baran
 */
public class Database {
    
    private String url="jdbc:oracle:thin:@localhost:1521:xe";
    private String login= "user1";
    private String password="101299";
    public  ArrayList<Integer> IDs;
    public void Init() throws SQLException 
    {
        Locale.setDefault(Locale.ENGLISH);
    }
    
     public Connection getConnection() throws SQLException
     {
         try{
         Class.forName( "oracle.jdbc.OracleDriver"); 
         }
         catch(ClassNotFoundException exp)
         {
             System.out.print("класс не найден");
         }
        return DriverManager.getConnection(url, login, password);
     }
     
    
    public void createModel() throws SQLException //метод создания базы данных
    {
        //создание пользователей
        Point point1=new Point(1);
        point1.inputList("Point1");
        Point point2=new Point(2);
        point2.inputList("Point2");
        Point point3=new Point(3);
        point3.inputList("Point3");
        Point point4=new Point(4);
        point4.inputList("Point4");
        Point point5=new Point(5);
        point5.inputList("Point5");
        Point point6=new Point(6);
        point6.inputList("Point6");
       
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
        user2.addPoint(1, point3);
        user3.addPoint(0, point2);
        user3.addPoint(1, point6);
        user3.addPoint(2, point4);
        user4.addPoint(0, point5);
        user4.addPoint(1, point3);
        user5.addPoint(0, point2);
        user5.addPoint(1, point4);
        user5.addPoint(2, point6);
       
       //соединение с Oracle при помощи JDBC
       Connection connection=getConnection();
       connection.setAutoCommit(false);//отключаем автотранзакции 
       Statement statement = connection.createStatement();
	// Для Insert, Update, Delete
       try{
       statement.executeUpdate("create table Points(id int primary key)");
       statement.executeUpdate("create table cars(ID int Primary key check(ID>0),"
               + "model varchar(100),"
                + "manufacturer varchar(100),"
                + "quantity int check(quantity>0),"
                + "cost int check(cost>0),"
                + "point int check(point>=0),"
                + "foreign key (point) references Points(id))");
        statement.executeUpdate("create table Users(id int primary key,name varchar(100),login varchar(100) unique,password varchar(100))");
        statement.executeUpdate("create table UserPoint(id_user int  references Users(id),id_point int references Points(id), primary key(id_user,id_point))");
        this.addUser(user1);
        this.addUser(user2);
        this.addUser(user3);
        this.addUser(user4);
        this.addUser(user5);
       }
       catch(SQLException exp)
       {
           System.out.println("Ошибка при создании таблиц");
       }
       connection.commit();//завершаем запросы только в случае успеха всех запросов
       connection.close();
    }
    
    
       public void addCar(Car car) throws SQLException //Добавление машины в базу данных
       { 
	//Statement statement = connection.createStatement();
        try
        {
        Connection connection=getConnection();    
        PreparedStatement statement = connection.prepareStatement("insert into cars(ID,model,manufacturer,quantity,cost,point) values(?,?,?,?,?,?)");
        statement.setInt(1,car.getID());
        statement.setString(2, car.getModel());
	statement.setString(3, car.getManufacturer());
        statement.setInt(4,car.getQuantity());
        statement.setInt(5,car.getCost());
        statement.setInt(6,car.getPoint().getID());
        statement.executeUpdate();
        }
        catch(SQLException exp)
        {
        
             if(exp.getErrorCode()==955||exp.getErrorCode()==1)
                System.out.println("машина с таким ID уже существует");
             
             else if(exp.getErrorCode()==2291)
                System.out.println("Машина не принадлежит ни одной точке");
             
             else
             System.out.println("SQLexception при добавлении машины "+exp.getErrorCode());
        }
       }
       
       
       public void addPoint(Point point) throws SQLException {//добавление точки в базу данных
	 try
         {
            Connection connection=getConnection();
            PreparedStatement statement = connection.prepareStatement("insert into points values(?)");
            statement.setInt(1, point.getID());
            statement.executeUpdate();
            for(int i=0;i<point.getLengthArray();i++)
            {
                this.addCar(point.getElement(i));
            }
          }
          catch(SQLException exp)
         {
             if(exp.getErrorCode()==955||exp.getErrorCode()==1)
                System.out.println("Точка с таким ID уже существует");
        }
               
       }
    
    public void addUser(User user) throws SQLException {//добавление пользователя в базу данных
	 
        try{
        Connection connection=getConnection();
        PreparedStatement statementUser = connection.prepareStatement("insert into users values(?,?,?,?)");    
        PreparedStatement statementUserpoint = connection.prepareStatement("insert into userpoint values(?,?)");  
        statementUser.setInt(1, user.getID());
        statementUser.setString(2, user.getName());
        statementUser.setString(3, user.getLogin());
        statementUser.setString(4, user.getPassword());
	statementUser.executeUpdate();
	
       for(int i=0;i<user.getPointSize();i++)
       {
           this.addPoint(user.getPoint(i));
           statementUserpoint.setInt(1, user.getID());
           statementUserpoint.setInt(2, user.getPoint(i).getID());
           statementUserpoint.executeUpdate();
       }
      }
       catch(SQLException exp)
        {
             if(exp.getErrorCode()==955||exp.getErrorCode()==1)
                System.out.println("Пользователь с таким ID уже существует");
        }
      	
}
    
      public void deleteCar(Car car) {
	try{
        Connection connection=getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareStatement("delete from cars where id = ?");  
        statement.setInt(1, car.getID());
        statement.executeUpdate();
        connection.commit();
        connection.close();
        }
        catch(SQLException exp)
        {
            System.out.println("Ошибка при удалении");
        }
       	
}
      
    public void deleteUser(User user) throws SQLException {
	Connection connection=getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statementUser = connection.prepareStatement("delete from users where id = ?");  
        PreparedStatement statementUserpoint = connection.prepareStatement("delete from userpoint where id_user = ?");  
        statementUserpoint.setInt(1,user.getID());
        statementUserpoint.executeUpdate();
        statementUser.setInt(1,user.getID());
        statementUser.executeUpdate();
        connection.commit();
        connection.close();
        
}
    
      public void deletePoint(Point point) throws SQLException {
	Connection connection=getConnection();
        connection.setAutoCommit(false);
        PreparedStatement statementCars = connection.prepareStatement("delete from cars where point = ?");  
        PreparedStatement statementUserpoint = connection.prepareStatement("delete from userpoint where id_point = ?");  
        PreparedStatement statementPoint = connection.prepareStatement("delete from points where id =?");  
        
        
        statementCars.setInt(1, point.getID());
        statementCars.executeUpdate();
        statementUserpoint.setInt(1, point.getID());
        statementUserpoint.executeUpdate();
        statementPoint.setInt(1, point.getID());
        statementPoint.executeUpdate();
        connection.commit();
        connection.close();
}
    
    public User getUserPoints(User user) //возвращает пользователя с его точками 
    {
       User getUser=null;
       try{
       Connection connection=getConnection();
       connection.setAutoCommit(false);
       Statement statement = connection.createStatement();
     
        ResultSet rs = statement.executeQuery("select * from users where login = '"+user.getLogin()+ "'");
        rs.next();
        getUser=new User();
        getUser.setID(rs.getInt("id"));
        getUser.setName(rs.getString("name"));
        getUser.setLogin(rs.getString("login"));
        getUser.setPassword(rs.getString("password"));
        
        ResultSet rs2 = statement.executeQuery("select id_point from userpoint where id_user= ' "+getUser.getID()+"'");
        int i=0;
         while(rs2.next()){
         //Retrieve by column name
         int id  = rs2.getInt("id_point");
         getUser.addPoint(i, getPointCars(id));
         i++;
         
        
      }
        connection.commit();
        connection.close();
      rs.close();
       }
       catch(SQLException exp)
       {
           System.out.print("Данного пользователя нет в базе");
       }
       
       return getUser;
    }
    
    
        public Point getPointCars(int ID) throws SQLException//возврщает точку с её машинами из базы данных
    {
        Connection connection=getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from cars where point=" +ID);
        Point point=new Point(ID);
        int i=0;
         while(rs.next()){
           
         point.addElement(i,new Car(rs.getInt("id"),rs.getString("model"),rs.getString("manufacturer"),rs.getInt("quantity"),rs.getInt("cost")));
        i++;
      }
      rs.close();
        connection.commit();
        connection.close();
      return point;
    }
        
        public boolean existUser(String login, String password) throws SQLException//проверяет существование пользователя
        {
             Connection connection=getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from users where login = ? and password=?");  
             statement.setString(1, login);
             statement.setString(2, password);
             ResultSet rs = statement.executeQuery();
             return(rs.next());
        }
        
//          public User getUserLogin(String login) 
//        {
//             try{
//             Connection connection=getConnection();
//             PreparedStatement statement = connection.prepareStatement("select * from users where login = ? ");  
//             statement.setString(1, login);
//     
//             ResultSet rs = statement.executeQuery();
//             rs.next();
//             return( new User(rs.getInt("id"),rs.getString("name"),rs.getString("login"),rs.getString("password")));
//             }
//             catch(SQLException sq)
//             {
//                 System.out.print("Ошибко");
//             }
//             return null;
//             }
        
        public ArrayList<Car> getCheapCar() throws SQLException//выводит массив самых дешёвых машин
        {
             ArrayList<Car> car=new ArrayList<Car>();
             Connection connection=getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from Cars where cost=(select distinct min(cost) from cars)");  
             ResultSet rs = statement.executeQuery();
             while(rs.next()){
             car.add(new Car(rs.getInt("id"),rs.getString("model"),rs.getString("manufacturer"),rs.getInt("quantity"),rs.getInt("cost")));
             }
             return(car);
        }
        
        
        public ArrayList<Integer> updateCarID() throws SQLException
        {
             ArrayList<Integer> temp=new ArrayList<Integer>();
             Connection connection=getConnection();
             PreparedStatement statement = connection.prepareStatement("select ID from Cars ");  
             ResultSet rs = statement.executeQuery();
             while(rs.next()){
             temp.add(rs.getInt("ID"));
             }
             
             return temp;
           
        }
       
       
}
