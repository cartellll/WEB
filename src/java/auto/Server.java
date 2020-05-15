/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;
import java.rmi.registry.*;
import java.rmi.Remote;
import java.rmi.AlreadyBoundException;
import java.rmi.server.UnicastRemoteObject;
import java.io.IOException;
import java.rmi.RemoteException;
/**
 *
 * @author baran
 */

public class Server {

    public static final String UNIC_BINDING_NAME = "sort";
    
    
    public static void main(String[] args){
       
        try{
        ServerSort sor=new ServerSort(); //создание класса для сортировки 
        final Registry registry = LocateRegistry.createRegistry(2099);//создаём реестр
        Remote stub = UnicastRemoteObject.exportObject(sor, 0);//создаём заглушку 
        registry.bind(UNIC_BINDING_NAME, stub);
        Thread.sleep(Integer.MAX_VALUE);
        }
        
        catch(RemoteException exp)
        {
            System.out.print("Ошибка RMI");
        }
        
        catch(InterruptedException exp)
        {
            System.out.print("Ошибка потоков");
        }
        
        catch(AlreadyBoundException exp)
        {
            System.out.print("Заглушка уже существует");
        }
    }
    
    
    
 
}
