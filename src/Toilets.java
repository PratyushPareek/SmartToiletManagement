
//import java.sql.*;
import java.util.*;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
public class Toilets 
{
    public static Vector<Toilet> ToiletTable = new Vector<Toilet>();
    public static Deque<Integer> MQueue = new ArrayDeque<Integer>();
    public static Deque<Integer> FQueue = new ArrayDeque<Integer>();
    
    public static void setup()
    {
        /*
        Connection conn = getConnection();
        try
        {
            Statement stmt = (Statement)conn.createStatement();
            String q = "SELECT * FROM Toilets ORDER BY ID;";
            ResultSet sqltable = stmt.executeQuery(q);
            
            while(sqltable.next())
            {
                int i = sqltable.getInt("ID");
                String g = sqltable.getString("Gender");
                ToiletTable.add(new Toilet(i,g));
            }
        }catch(Exception e){System.out.println(e);}
        */
        ToiletTable.add(new Toilet(1,"M"));
        ToiletTable.add(new Toilet(2,"F"));
        ToiletTable.add(new Toilet(3,"M"));
        ToiletTable.add(new Toilet(4,"F"));
        ToiletTable.add(new Toilet(5,"M"));
    }
    
    public static void checkCycle()
    {
        for(Toilet t: ToiletTable)
        {
            if(t.isClean)
            {
                t.getConditions();
                if(!t.checkConditions())
                {    
                    if(t.gender.contains("M"))
                        MQueue.addLast(t.id);
                    else FQueue.addLast(t.id);
                    try{loadpage.log.writer.write(new java.util.Date(System.currentTimeMillis()) +" | Toilet "+t.id+" is unclean. | Gas Sensor: "+String.format("%.2f",t.gs.value)+"V |"+"Turbidity Sensor: "+String.format("%.2f",t.ts.value)+"NTU |\n");}
                    catch(Exception e){System.out.println(e);}
                }
            }
        }            
    }
    
    public static Toilet findToiletByID(int id)
    {
        try{
            for(Toilet t : ToiletTable)
            {
                if(t.id==id)
                    return t;
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
        return null;
    }
    
    /*
    public static Connection getConnection() 
    {
        try{
		String url = "jdbc:mysql://localhost:3306/Pristine";
		String username = "Shade";
		String password = "Philadelphia";
		Connection conn = DriverManager.getConnection(url,username,password);
                return conn;
        }catch(Exception e){System.out.println(e);}
        return null;
    }
    */
}
