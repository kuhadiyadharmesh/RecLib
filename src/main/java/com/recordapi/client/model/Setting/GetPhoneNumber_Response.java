package com.recordapi.client.model.Setting;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetPhoneNumber_Response
{
    //[{"phone_number":"+15635626260","number":"5635626260","prefix":"+1","friendly_name":"(563)562-6260","flag":"https:\/\/app2.virtualbrix.net\/assets\/flags\/icons\/us.png"}]
    private String phone_number , number , prefix , friendly_name , flag ,msg ,country;
    private boolean status = false;


    public GetPhoneNumber_Response(String phone_number,String number , String prefix, String friendly_name,String flag,String country)
    {
        this.status = true ;
        this.phone_number = phone_number;
        this.number = number;
        this.prefix = prefix;
        this.friendly_name = friendly_name ;
        this.flag = flag ;
        this.msg = "get succuessfully";
        this.country = country;
    }
    public GetPhoneNumber_Response(String msg)
    {
        this.msg = msg;
        this.status = false ;
    }

    public boolean getStatus()
    {
        return this.status;
    }
    public String getMsg()
    {
        return this.msg;
    }
    public String getPhone_number()
    {
        return this.phone_number;
    }
    public String getNumber()
    {
        return this.number;
    }
    public String getPrefix()
    {
        return this.prefix;
    }
    public String getFriendly_name()
    {
        return this.friendly_name;
    }
    public String getFlag()
    {
        return this.flag;
    }
    public String getCountry()
    {return this.country;}

}
