/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
import java.lang.*;
import java.util.*;

class BGThreads implements Runnable
{
    @Override
    public void run() 
    {
        //int n=20;
        while(true)
        {
            Toilets.checkCycle();
            Staff.assignCycle();
            //System.out.println(n);
            try{Thread.sleep(1000);}catch(Exception e){System.out.println(e);}
        }    
    }
}

public class testMain 
{
    public static void main(String args[])
   {
        Staff.setup();
        Toilets.setup();
        
        new Thread(new BGThreads()).start();
        
        try{Thread.sleep(20000);}catch(Exception e){System.out.println(e);}
        System.out.println("");
        for(StaffMember j : Staff.StaffTable)
            System.out.println(j.id + " " + j.name + " " + j.email + " " + j.contactNo + " " + j.gender + " " + j.isWorking.toString());
        System.out.println("");
        for(Toilet t : Toilets.ToiletTable)
            System.out.println(t.id + " " + t.gender + " " + t.isClean.toString());
          
   }        
}
