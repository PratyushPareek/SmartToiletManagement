
import java.sql.*;
import java.util.*;

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
        //Got the connection
        Connection conn = getConnection();
        try
        {
            //Created a statement
            Statement stmt = (Statement)conn.createStatement();
            String q = "SELECT * FROM Toilets ORDER BY ID;";
            //Caught query's return 
            ResultSet sqltable = stmt.executeQuery(q);
            
            //Added it to Vector
            while(sqltable.next())
            {
                int i = sqltable.getInt("ID");
                String g = sqltable.getString("Gender");
                ToiletTable.add(new Toilet(i,g));
                if(g=="M")
                    MQueue.add(i);
                else FQueue.add(i);
            }
        }catch(Exception e){System.out.println(e);}  
    }
    
    public static void checkCycle()
    {
            for(Toilet t: ToiletTable)
            {
                if(!t.checkConditions())
                {
                    if(t.gender=="M")
                        MQueue.addLast(t.id);
                    else FQueue.addLast(t.id);
                    t.isClean = false;
                }
            }
    }        

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
    
    public static int generateID()
    {
        int id = -1;
        for(Toilet t : ToiletTable)
            id = (t.id>id)?t.id:id;
        return ++id;
    }
}
