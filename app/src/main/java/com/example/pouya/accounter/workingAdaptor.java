package com.example.pouya.accounter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pouya on 10/12/2018.
 */

public class workingAdaptor extends ArrayAdapter<workingClass> {

    public workingAdaptor(@NonNull Context context, List<workingClass> scrolledShift) {
        super(context,0, scrolledShift);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.working_list_item,parent,false);
        }
        workingClass currentShift = getItem(position);

        TextView machineNumber = (TextView) listItemView.findViewById(R.id.machineNumber);
        GradientDrawable machine = (GradientDrawable) machineNumber.getBackground();
        int magnitudeColor = getMagnitudeColor(Integer.parseInt(currentShift.getTotalMeter()));
        machine.setColor(magnitudeColor);
        machineNumber.setText(currentShift.getMachineNumber());
        TextView newMeter = (TextView) listItemView.findViewById(R.id.newMeter);
        newMeter.setText(currentShift.getNewMeter());
        TextView oldMeter = (TextView) listItemView.findViewById(R.id.oldMeter);
        oldMeter.setText(currentShift.getOldMeter());
        TextView userID = (TextView) listItemView.findViewById(R.id.userID);
        userID.setText(currentShift.getPersonID());
        TextView totalMeter = (TextView) listItemView.findViewById(R.id.metrazh);
        totalMeter.setText(currentShift.getTotalMeter());



        return listItemView;

    }
    private int getMagnitudeColor(double color){
        int colorMag = ((int) Math.floor(color));
        int response;
        if(colorMag>1000){
            response = R.color.magnitude1;
        }else if(colorMag>900){
            response = R.color.magnitude2;
        }else if(colorMag>800){
            response = R.color.magnitude3;
        }else if(colorMag>700){
            response = R.color.magnitude4;
        }else if(colorMag>600){
            response = R.color.magnitude5;
        }else if(colorMag>500){
            response = R.color.magnitude6;
        }else if(colorMag>400){
            response = R.color.magnitude7;
        }else if(colorMag>300){
            response = R.color.magnitude8;
        }else{
            response = R.color.magnitude10plus;
        }
        Log.i("Response Log","Our absolute Mag is: "+color);
        Log.i("Response Log","Our int color: "+colorMag);
        Log.i("Response Log","Our response is: "+response);
        return ContextCompat.getColor(getContext(),response);
    }
}
