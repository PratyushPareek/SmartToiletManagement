/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author praty
 */
public class StaffMember 
{
    int id;
    String name;
    String email;
    long contactNo;
    String gender;
    Boolean isWorking;
    
    StaffMember(int id, String name, String email, long contactNo, String gender)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.isWorking = false;
    }
}
