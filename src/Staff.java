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
import java.lang.*;
import java.io.*;
import com.email.durgesh.Email;

class BGCleaned implements Runnable
{            
    int tid;
    String g;
    int smid;
            
    BGCleaned(int tid, String g, int smid)
    {
        this.tid = tid;
        this.g = g;
        this.smid = smid;
    }        
    @Override 
    public void run() 
    {
        try{Thread.sleep(3500+(long)(Math.random()*4000));}catch(Exception e){System.out.println(e);}
        Toilets.findToiletByID(tid).isClean = true;
        Staff.findMemberByID(smid).isWorking = false;
        try{loadpage.log.writer.write(new java.util.Date(System.currentTimeMillis())+" | Toilet "+tid+" was cleaned by "+Staff.findMemberByID(smid).name+"\n");}
        catch(Exception e){System.out.println(e);}
        if(g.contains("M"))
            Staff.MStaffQueue.addLast(smid);
        else Staff.FStaffQueue.addLast(smid);   
    }
}

public class Staff 
{
    public static Vector<StaffMember> StaffTable = new Vector<StaffMember>();
    public static Deque<Integer> MStaffQueue = new ArrayDeque<Integer>();
    public static Deque<Integer> FStaffQueue = new ArrayDeque<Integer>();
    
    public static void setup()
    {
        /*
        Connection conn = getConnection();
        try
        {
            Statement stmt = (Statement)conn.createStatement();
            String q = "SELECT * FROM Staff ORDER BY ID;";
            ResultSet sqltable = stmt.executeQuery(q);
            
            while(sqltable.next())
            {
                int i = sqltable.getInt("ID");
                String n = sqltable.getString("Name");
                String e = sqltable.getString("Email");
                long c = sqltable.getLong("ContactNo");
                String g = sqltable.getString("Gender");
                StaffTable.add(new StaffMember(i,n,e,c,g));
                if(g.contains("M"))
                    MStaffQueue.add(i);
                else FStaffQueue.add(i);
            }
        }catch(Exception e){System.out.println(e);} 
        */
        StaffTable.add(new StaffMember(2,"Arya Stark","noOne@fakemail.com",1234567890,"F"));
        StaffTable.add(new StaffMember(3,"Jon Snow","bastard@fakemail.com",1834567890,"M"));
        StaffTable.add(new StaffMember(4,"Sansa Stark","princess@fakemail.com",1294567890,"F"));
        StaffTable.add(new StaffMember(5,"Robb Stark","wolfie@fakemail.com",1634567890,"M"));
        StaffTable.add(new StaffMember(6,"Catelyn Stark","cat@fakemail.com",1934567890,"F"));
        StaffTable.add(new StaffMember(7,"Theon Greyjoy","reek@fakemail.com",1534567890,"M"));
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
        if(n.gender.contains("M"))
            MStaffQueue.addFirst(n.id);
        else FStaffQueue.addFirst(n.id);
        /*
        Connection conn = getConnection();
        try{
            Statement stmt = (Statement)conn.createStatement();
            String u = "INSERT INTO Staff VALUES("+n.id+",'"+n.name+"','"+n.email+"',"+n.contactNo+",'"+n.gender+"');";
            stmt.executeUpdate(u);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);}  
        */
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
        /*
        Connection conn = getConnection();
        try{
            Statement stmt = (Statement)conn.createStatement();
            String u = "DELETE FROM Staff WHERE ID="+id+";";
            stmt.executeUpdate(u);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);} 
        */
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
        /*
        Connection conn = getConnection();
        try{
            Statement stmt = (Statement)conn.createStatement();
            String u = "DELETE FROM Staff WHERE ID="+t.id+";";
            String v = "INSERT INTO Staff VALUES("+t.id+",'"+t.name+"','"+t.email+"',"+t.contactNo+",'"+t.gender+"');";
            stmt.executeUpdate(u);
            stmt.executeUpdate(v);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e);} 
        */
    }        
    
    public static void assignCycle()
    {
        if(!Toilets.MQueue.isEmpty()&&!MStaffQueue.isEmpty())
        {
            sendAlert(Toilets.MQueue.getFirst(),MStaffQueue.getFirst());
            findMemberByID(MStaffQueue.getFirst()).isWorking = true;
            try{loadpage.log.writer.write(new java.util.Date(System.currentTimeMillis())+" | Toilet "+Toilets.MQueue.getFirst()+" is being cleaned by "+Staff.findMemberByID(MStaffQueue.getFirst()).name+"\n");}
            catch(Exception e){System.out.println(e);}
        
            new Thread(new BGCleaned(Toilets.MQueue.getFirst(),"M",MStaffQueue.getFirst())).start();
            
            Toilets.MQueue.removeFirst();
            MStaffQueue.removeFirst();
        }
        if(!Toilets.FQueue.isEmpty()&&!FStaffQueue.isEmpty())
        {
            sendAlert(Toilets.FQueue.getFirst(),FStaffQueue.getFirst());
            findMemberByID(FStaffQueue.getFirst()).isWorking = true;
            try{loadpage.log.writer.write(new java.util.Date(System.currentTimeMillis())+" | Toilet "+Toilets.FQueue.getFirst()+" is being cleaned by "+Staff.findMemberByID(FStaffQueue.getFirst()).name+"\n");}
            catch(Exception e){System.out.println(e);}
            
            new Thread(new BGCleaned(Toilets.FQueue.getFirst(),"F",FStaffQueue.getFirst())).start();
            
            Toilets.FQueue.removeFirst();
            FStaffQueue.removeFirst();
        }
    }        
    
    public static int generateID()
    {
        int id = 0;
        for(StaffMember sm : StaffTable)
            id = (sm.id>id)?sm.id:id;
        return ++id;
    }
    
    public static void sendAlert(int tid, int smid)
    {
        if(!findMemberByID(smid).email.contains("fakemail"))
        {
            try
            {
                Email mail = new Email("nightmareDarkrai26@gmail.com","don'tLook26*");
                mail.setFrom("nightmareDarkrai26@gmail.com","Crimson Shade");
                mail.setSubject("Get back to work");
                mail.setContent("<body>Dear "+findMemberByID(smid).name+",<br>"+"You have been assigned to clean Toilet <b>"+tid+"<b>. Get back to work immediately.<br>"+"<br>Yours truly, <br>Admin Gupta</body>" , "text/html");
                mail.addRecipient(findMemberByID(smid).email);
                mail.send();
            }
            catch(Exception e){System.out.println(e);}    
        }
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
    }*/
}
