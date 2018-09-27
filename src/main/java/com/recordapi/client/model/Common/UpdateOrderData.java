package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 5/8/2018.
 */

public class UpdateOrderData
{
    /*
        type= file or folder
        id=2 //item id
        index= 0 - 200 //0 is for first
     */
    private int id ,top_id ;
    private String apikey;
//    private int  ;


    public UpdateOrderData(String apikey,int id , int top_id)
    {
        this.id = id ;
        this.top_id = top_id;
        this.apikey = apikey;
    }

    public int getId(){return this.id;}
    public int getTop_Id(){return this.top_id;}
    public String getApikey(){return this.apikey;}
}
