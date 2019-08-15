package com.example.pouya.accounter;

/**
 * Created by pouya on 10/24/2018.
 */

public class machineClass {
    private String machineNumber;
    private String salonNumber;

    public machineClass(String machine, String salon) {
        machineNumber = machine;
        salonNumber = salon;
    }


    public String getMachineNumber(){return machineNumber;}
    public String getSalonNumber(){return salonNumber;}

}
