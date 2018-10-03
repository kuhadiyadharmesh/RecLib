package com.recordapi.client.model.Common;

/**
 * Created by Dharmesh-PC on 1/13/2018.
 */

public class Language
{
    //":[{"code":"en_US","name":"EnglishUS"},{"

    private  String code , name ;

    public Language(String code , String name )
    {
        this.code = code;
        this.name = name;
    }
    public String getCode()
    {
        return  this.code;
    }
    public String getName()
    {
        return this.name;
    }
}
