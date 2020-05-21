/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto;

import java.rmi.RemoteException;
import java.rmi.Remote;
import ObjectClass.Point;
/**
 *
 * @author baran
 */
public interface Sort extends Remote{
    public Point sort(Point a) throws RemoteException;
}
