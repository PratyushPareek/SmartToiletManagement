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
    public static boolean B;
    @Override
    public void run() 
    {
        while(B)
        {
            Toilets.checkCycle();
            Staff.assignCycle();
            try{Thread.sleep(1000);}catch(Exception e){System.out.println(e);}
        }    
    }
}


