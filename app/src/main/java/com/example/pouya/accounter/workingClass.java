package com.example.pouya.accounter;

/**
 * Created by pouya on 10/24/2018.
 */

public class workingClass {
    private String newMeter;
    private String oldMeter;
    private String machineNumber;
    private String ring;
    private String personID;
    private String totalMeter;
    private String weaveType;
    private String extra;

    public workingClass(String newMeterRead, String oldMeterRead, String machineNumberRead, String ringRead, String personIDRead, String endMeter, String weaveTypeRead, String extraRead) {
        newMeter=newMeterRead;
        oldMeter = oldMeterRead;
        machineNumber = machineNumberRead;
        ring = ringRead;
        personID = personIDRead;
        totalMeter = endMeter;
        weaveType = weaveTypeRead;
        extra = extraRead;
    }


    public String getNewMeter(){return newMeter;}
    public String getOldMeter(){return oldMeter;}
    public String getMachineNumber(){return machineNumber;}
    public String getRing(){return ring;}
    public String getPersonID(){return personID;}
    public String getTotalMeter(){return totalMeter;}
    public String getWeaveType(){return weaveType;}
    public String getExtra(){return getExtra();}

    public String calculateTotalMeter(String oldMeter,String newMeter,String finalMeter){
        String meter;
        if(finalMeter.equals("0")){
            meter = ""+(Integer.parseInt(newMeter)-Integer.parseInt(oldMeter));
        }else{
            meter = ""+(Integer.parseInt(finalMeter)-Integer.parseInt(oldMeter)+Integer.parseInt(newMeter));
        }
        return meter;

    }
}
