package com.recordapi.client.model.Meta;

/**
 * Created by Dharmesh-PC on 1/16/2018.
 */

public class DeleteMetaFiles
{
    //"api_key=553a431e192d2553a431e1930f&ids=1,2,4"  // or parent_id=19

    private String api_key ,ids;
    private int parent_id;

    public DeleteMetaFiles(String api_key,String ids)// ids = 1,2,3,4 -- multiple allowd
    {
        this.api_key = api_key;
        this.ids = ids;
    }

    public DeleteMetaFiles(String api_key, int parent_id)// only one parent id
    {
        this.api_key = api_key ;
        this.parent_id = parent_id;
    }

    public String getApi_key()
    {
        return this.api_key;
    }
    public String getIds()
    {
        return this.ids;
    }
    public int getParent_id()
    {
        return this.parent_id;
    }
}
