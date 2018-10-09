package com.recordapi.client.model.Common;

import java.io.Serializable;

/**
 * Created by Dharmesh-PC on 1/11/2018.
 */

public class FileData implements Serializable
{
    //{
        //    "id": "12",
        //            "access_number": "",
        //            "name": "Untitled4",
        //            "f_name": "",
        //            "l_name": "",
        //            "email": "",
        //            "phone": "",
        //            "notes": "notes",
        //            "meta": "",
        //            "source": "",
        //            "url": "https://app2.virtualbrix.net/records/player/file/32/557931e49b4f9_1434005988_71056787.mp3",
        //            "credits": "0",
        //            "duration": "1",
        //            "time": "1434005988",
        //            "share_url": "https://app2.virtualbrix.net/records/player/file/32/557931e49b4f9_1434005988_71056787.mp3",
        //            "download_url": "https://app2.virtualbrix.net/records/player/file/32/download/557931e49b4f9_1434005988_71056787.mp3"
        //}

    /*

     f_name=first&
    l_name=lasttest&
    notes=test_notes
    &email=test@gmail.com&
    phone=%2B18104763057&
    tags=tagone&
    meta[duration]=00:20&
    meta[note]=metanote&
    meta[url]=google&
    folder_id=0&
    name=first reording&
    remind_days=10&
    remind_date=10-jan-2017 23:23:52
     */

    private String id , access_number , sid , name , f_name , l_name , email , phone,notes,meta, source , url , credits , duration , time , share_url , download_url ;
    private String meta_duration , meta_url , meta_notes , folder_id , reminder_days, reminder_dates,tags , is_star , order_id ;


    public FileData(String id )
    {
        this.id = id ;
    }

    // For Create Files
    public FileData(String id , String access_number,String name , String f_name , String l_name , String email ,String phone , String notes , String meta , String source , String url , String credits, String duration , String time , String share_url , String download_url , String is_star )
    {
        this.id = id ;
        this.access_number = access_number;
        this.name = name ;
        this.f_name = f_name ;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.meta = meta;
        this.source = source;
        this.url =url;
        this.credits = credits;
        this.duration = duration ;
        this.time = time ;
        this.share_url = share_url;
        this.download_url = download_url;
        this.is_star = is_star;
    }

    // For Create Files
    public FileData(String id , String order_id , String sid,String name , String f_name , String l_name , String email ,String phone , String notes  , String source , String url ,  String duration , String time , String share_url , String download_url , String is_star ,String remind_days, String remind_date)
    {
        this.id = id ;
        this.order_id = order_id;
        this.name = name ;
        this.f_name = f_name ;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        //this.meta = meta;
        this.source = source;
        this.url =url;
        this.sid = sid;
        this.duration = duration ;
        this.time = time ;
        this.share_url = share_url;
        this.download_url = download_url;
        this.is_star = is_star;
        this.reminder_days = remind_days;
        this.reminder_dates = remind_date;


    }


    // For update FIle
    public FileData(String id , String name , String f_name , String l_name , String email ,String phone , String notes , String meta_duration , String meta_note , String meta_url , String folder_id, String reminder_days , String remider_dates,String tags)
    {
        this.id = id ;
//        this.access_number = access_number;
        this.name = name ;
        this.f_name = f_name ;
        this.l_name = l_name;
        this.email = email;
        this.phone = phone;
        this.notes = notes;
        this.meta_duration = meta_duration;
        this.meta_notes = meta_note;
        this.meta_url = meta_url;
        this.folder_id = folder_id;
        this.reminder_dates = remider_dates;
        this.reminder_days = reminder_days;
        this.tags = tags;
        //this.IsStar = IsStar;

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAccess_number(String access_number) {
        this.access_number = access_number;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public void setMeta_duration(String meta_duration) {
        this.meta_duration = meta_duration;
    }

    public void setMeta_url(String meta_url) {
        this.meta_url = meta_url;
    }

    public void setMeta_notes(String meta_notes) {
        this.meta_notes = meta_notes;
    }

    public void setFolder_id(String folder_id) {
        this.folder_id = folder_id;
    }

    public void setReminder_days(String reminder_days) {
        this.reminder_days = reminder_days;
    }

    public void setReminder_dates(String reminder_dates) {
        this.reminder_dates = reminder_dates;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setIs_star(String is_star) {
        this.is_star = is_star;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getId()
    {
        return this.id;
    }
    public String getAccess_number()
    {
        return this.access_number;
    }
    public String getName()
    {
        return this.name;
    }
    public String getF_name()
    {
        return this.f_name;
    }
    public String getL_name()
    {
        return this.l_name;
    }
    public String getEmail()
    {
        return this.email;
    }
    public String getPhone()
    {
        return this.phone;
    }
    public String getNotes()
    {
        return this.notes;
    }
    public String getMeta()
    {
        return this.meta;
    }
    public String getSource()
    {
        return this.source;
    }
    public String getUrl()
    {
        return url;
    }
    public String getCredits()
    {
        return this.credits;
    }
    public String getDuration()
    {
        return this.duration;
    }
    public String getTime()
    {
        return this.time;
    }
    public String getShare_url()
    {
        return  this.share_url;
    }
    public String getDownload_url()
    {
        return this.download_url;
    }


    public String getMeta_duration()
    {
        return this.meta_duration;
    }
    public String getMeta_url()
    {
        return this.meta_url;
    }
    public String getMeta_notes()
    {
        return this.meta_notes;
    }
    public String getFolder_id()
    {
        return this.folder_id;
    }
    public String getReminder_days()
    {
        return this.reminder_days;
    }
    public String getReminder_dates()
    {
        return this.reminder_dates;
    }
    public String getTags()
    {
        return this.tags;
    }
    public String getIs_Star()
    {
        return this.is_star;
    }

}
