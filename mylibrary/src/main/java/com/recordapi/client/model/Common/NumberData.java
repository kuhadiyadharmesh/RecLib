package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 9/27/2018.
 */

public class NumberData
{
    private String phone_number , number , prefix , friendly_name , flag ,msg ,country;
    public NumberData(String phone_number,String number , String prefix, String friendly_name,String flag,String country)
    {

        this.phone_number = phone_number;
        this.number = number;
        this.prefix = prefix;
        this.friendly_name = friendly_name ;
        this.flag = flag ;
        this.msg = "get succuessfully";
        this.country = country;
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
