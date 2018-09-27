package com.recordapi.client.model.Setting;

import com.recordapi.client.model.Common.NumberData;

import java.util.List;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class GetPhoneNumber_Response
{
    //[{"phone_number":"+15635626260","number":"5635626260","prefix":"+1","friendly_name":"(563)562-6260","flag":"https:\/\/app2.virtualbrix.net\/assets\/flags\/icons\/us.png"}]
    private String msg ;
    private boolean status = false;
    private List<NumberData> data ;

    public GetPhoneNumber_Response(String msg, List<NumberData> data)
    {
        this.status = true ;

        this.msg = "get succuessfully";
        //this.country = country;
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
    public List<NumberData> getNumberData()
    {
        return  this.data;
    }



}
