package com.example.pouya.accounter;

/**
 * Created by pouya on 10/24/2018.
 */

public class WeaverClass {
    private String weaverName;
    private String weaverPesonelID;

    public WeaverClass(String name, String id) {
        weaverName = name;
        weaverPesonelID = id;
    }


    public String getWeaverName(){return weaverName;}
    public String getWeaverPesonelID(){return weaverPesonelID;}

}
