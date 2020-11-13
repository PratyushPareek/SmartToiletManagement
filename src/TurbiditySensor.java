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
    public static double threshold = 600;
    
    Boolean checkCondition()
    {
        if(Math.random()*900<threshold)
            return true;
        else return false;
    }
}
