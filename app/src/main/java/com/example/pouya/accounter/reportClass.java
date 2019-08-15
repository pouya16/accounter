package com.example.pouya.accounter;

/**
 * Created by pouya on 10/24/2018.
 */

public class reportClass {
    private String date;
    private String shiftNumber;
    private String report;

    public reportClass(String readDate, String readShiftNumber, String readReport) {
        date = readDate;
        shiftNumber = readShiftNumber;
        report = readReport;
    }


    public String getDate(){return date;}
    public String getShiftNumber(){return shiftNumber;}
    public String getReport(){return report;}

}
