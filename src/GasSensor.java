/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
public class GasSensor 
{
    public static double threshold = 4.5;
    double value;
    
    GasSensor()
    {
        this.value = 0;
    }        
    
    void getCondition()
    {
        this.value = Math.random()*5;
    }
    
    Boolean checkCondition()
    {
        if(this.value<threshold)
            return true;
        else return false;
    }
}
