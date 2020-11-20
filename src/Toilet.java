/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
public class Toilet 
{
    int id;
    TurbiditySensor ts;
    GasSensor gs;
    String gender;
    Boolean isClean;
    
    Toilet(int id, String g)
    {
        this.id=id;
        this.gender = g;
        this.ts = new TurbiditySensor();
        this.gs = new GasSensor();
        this.isClean = true;
    }        
    
    void getConditions()
    {
        gs.getCondition();
        ts.getCondition();
    }        
    
    Boolean checkConditions()
    {
        if(!(ts.checkCondition()||gs.checkCondition()))
        {
            this.isClean = false;
            return false;
        }
        else return true;
    }   
}
