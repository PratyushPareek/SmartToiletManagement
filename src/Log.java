/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
import java.util.Date;
import java.io.*;

public class Log 
{
    Date initTime;
    File file;
    FileWriter writer;
    
    Log(Date time)
    {
        this.file = new File("Log "+time.toString().replace(":","_")+".txt");
        try
        {
            file.createNewFile();
            this.writer = new FileWriter(file);
        }
        catch(Exception e){System.out.println(e);} 
    }        
    
    
}
