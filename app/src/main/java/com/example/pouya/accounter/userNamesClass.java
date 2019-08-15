package com.example.pouya.accounter;

/**
 * Created by pouya on 10/24/2018.
 */

public class userNamesClass {
    private String id;
    private String userName;
    private String password;

    public userNamesClass(String user,String pass,String userID) {
        userName = user;
        password = pass;
        id = userID;
    }


    public String getUserName(){return userName;}
    public String getId(){return id;}
    public String getPassword(){return password;}

}
