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
    Genders gender;
    Boolean isWorking;
    
    StaffMember(int id, String name, String email, long contactNo, Genders gender)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.gender = gender;
        this.isWorking = false;
    }
    
    void setName(String newName)
    {
        this.name = newName;
    }
    void setEmail(String newEmail)
    {
        this.email = newEmail;
    }
    void setName(long newCNo)
    {
        this.contactNo = newCNo;
    }
    void setName(Genders newG)
    {
        this.gender = newG;
    }
    void SetStatus(Boolean b)
    {
        this.isWorking = b;
    }
    
    
    
    enum Genders
    {
        MALE,
        FEMALE,
        NONBINARY 
    }
}
