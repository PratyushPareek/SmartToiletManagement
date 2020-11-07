/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Staff 
{
    public static Vector<StaffMember> StaffTable = new Vector<StaffMember>();
    public static Deque<Integer> MStaffQueue = new ArrayDeque<Integer>();
    public static Deque<Integer> FStaffQueue = new ArrayDeque<Integer>();
    
    public static void setup()
    {
        //Got the connection
        Connection conn = getConnection();
        try
        {
            //Created a statement
            Statement stmt = (Statement)conn.createStatement();
            String q = "SELECT * FROM Staff ORDER BY ID;";
            //Caught query's return 
            ResultSet sqltable = stmt.executeQuery(q);
            
            //Added it to Vector
            while(sqltable.next())
            {
                int i = sqltable.getInt("ID");
                String n = sqltable.getString("Name");
                String e = sqltable.getString("Email");
                long c = sqltable.getLong("ContactNo");
                String g = sqltable.getString("Gender");
                StaffTable.add(new StaffMember(i,n,e,c,g));
                if(g=="M")
                    MStaffQueue.add(i);
                else FStaffQueue.add(i);
            }
        }catch(Exception e){System.out.println(e);}    
    }
    
    public static Vector<StaffMember> findMemberByName(String name)
    {
        name = name.trim().toLowerCase();
        Vector<StaffMember> ReturnTable = new Vector<StaffMember>();
        
        for(StaffMember sm : StaffTable)
        {
            String S = sm.name.trim().toLowerCase();
            if(S.contains(name))
                ReturnTable.add(sm);
        }
        return ReturnTable;
    }        
    
    public static StaffMember findMemberByID(int id)
    {
        try{
            for(StaffMember sm : StaffTable)
            {
                if(sm.id==id)
                    return sm;
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
        return null;
    }
    
    public static void addMember (StaffMember n)
    {
        StaffTable.add(n);
        
        Connection conn = getConnection();
        try{
            Statement stmt = (Statement)conn.createStatement();
            String u = "INSERT INTO Staff VALUES("+n.id+",\""+n.name+"\",\""+n.email+"\","+n.contactNo+",\""+n.gender+");";
            stmt.executeUpdate(u);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
    }
    
    public static void deleteMember (int id)
    {
        for(int i=0; i<StaffTable.size(); i++)
        {
            if(StaffTable.get(i).id == id)
           {
               StaffTable.remove(i);
               break;
           }
        }
        Connection conn = getConnection();
        try{
            Statement stmt = (Statement)conn.createStatement();
            String u = "DELETE * FROM Staff WHERE ID="+id;
            stmt.executeUpdate(u);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}    
    }        
    
    public static void updateInfo(StaffMember t)
    {
        for(int i=0; i<StaffTable.size(); i++)
        {
            if(StaffTable.get(i).id == t.id)
           {
               StaffTable.get(i).name = t.name;
               StaffTable.get(i).email = t.email;
               StaffTable.get(i).contactNo = t.contactNo;
               StaffTable.get(i).gender = t.gender;
               break;
           }
        }
        
        Connection conn = getConnection();
        try{
            Statement stmt = (Statement)conn.createStatement();
            String u = "DELETE * FROM Staff WHERE ID="+t.id;
            String v = "INSERT INTO Staff VALUES("+t.id+",\""+t.name+"\",\""+t.email+"\","+t.contactNo+",\""+t.gender+");";
            stmt.executeUpdate(u);
            stmt.executeUpdate(v);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}  
    }        
    
    void getJanitor (int tid, String tg)
    {
        int smid;
        if(tg=="M")
        {
            smid = MStaffQueue.getFirst();
            MStaffQueue.removeFirst();
            MStaffQueue.addLast(smid);
            
        }
        else
        {    
            smid = FStaffQueue.getFirst();
            FStaffQueue.removeFirst();
            FStaffQueue.addLast(smid);
        }    
        try{
            for(StaffMember sm : StaffTable)
            {
                if(sm.id==smid)
                    sm.isWorking = true;
            }
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}
        sendAlert(tid,smid);
    }
    
    void sendAlert(int tid, int smid)
    {
        
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
}
