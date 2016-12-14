package com.example.ray.teachlink;

import java.util.ArrayList;

/**
 * Created by ray on 2016/12/14.
 */

public class UserInfo {
    private String username;
    private String password;
    private String job;
    private ArrayList<String> className;

    public UserInfo(String username,String password){
        this.username=username;
        this.password=password;
    }
    public boolean checkId(){
        boolean access=true;
        //access = login_sql;
        if(access) {
            //這裡還要查mysql user資料
            //sql***********
            return true;
        }else{
            return false;
        }
    }
}
