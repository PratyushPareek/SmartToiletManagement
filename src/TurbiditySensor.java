/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
public class TurbiditySensor 
{
    public static double threshold = 810;
    double value;
    
    TurbiditySensor()
    {
        this.value = 0;
    }        
    
    void getCondition()
    {
        this.value = Math.random()*900;
    }
    
    Boolean checkCondition()
    {
        if(this.value<threshold)
            return true;
        else return false;
    }
}
