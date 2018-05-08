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
    private int id ,index ;
    private String apikey;
//    private int  ;


    public UpdateOrderData(String apikey,int id , int index)
    {
        this.id = id ;
        this.index = index;
        this.apikey = apikey;
    }

    public int getId(){return this.id;}
    public int getIndex(){return this.index;}
    public String getApikey(){return this.apikey;}
}
