package com.netdroidtech.www.mylibrary;

/**
 * Created by Dharmesh-PC on 1/5/2018.
 */

public class RecApi
{
    String message ;
    public RecApi(String msg)
    {
        this.message = msg;
    }

    public void Log()
    {
        System.out.println(message);
    }
}
