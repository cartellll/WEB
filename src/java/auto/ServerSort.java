/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;
import java.rmi.RemoteException;
import ObjectClass.Point;
import java.util.*;
/**
 *
 * @author baran
 */
public class ServerSort implements Sort { // класс, реализующий интерфейс sort
    
    public Point sort(Point a) throws RemoteException
    {
        a.sort();
        return a;
    }
}
