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
        while(true)
        {
            Toilets.checkCycle();
            Staff.assignCycle();
            //System.out.println(n);
            try{Thread.sleep(1000);}catch(Exception e){System.out.println(e);}
        }    
    }
}


