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
    public static double threshold = 4;
    
    Boolean checkCondition()
    {
        if(Math.random()*5<threshold)
            return true;
        else return false;
    }
}
