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

public class reportAdaptor extends ArrayAdapter<reportClass> {

    public reportAdaptor(@NonNull Context context, List<reportClass> userNameList) {
        super(context,0, userNameList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.report_list_item,parent,false);
        }
        reportClass currentReport = getItem(position);

        TextView dateReport = (TextView) listItemView.findViewById(R.id.reportDate);
        GradientDrawable dateGradient = (GradientDrawable) dateReport.getBackground();
        int idColor= getMagnitudeColor(Integer.parseInt(currentReport.getShiftNumber()));
        dateGradient.setColor(idColor);
        int intDate = Integer.parseInt(currentReport.getDate());
        int day = intDate%100;
        intDate /= 100;
        int month = intDate%100;
        intDate /= 100;
        String formmated = "" + intDate + "/" + month + "/" + day;
        dateReport.setText(formmated);
        TextView reportShow = (TextView) listItemView.findViewById(R.id.report);
        reportShow.setText(currentReport.getReport());
        TextView shiftNumber  = (TextView) listItemView.findViewById(R.id.reportShift);
        shiftNumber.setText(currentReport.getShiftNumber());

        return listItemView;

    }
    private int getMagnitudeColor(double color){
        int colorMag = ((int) Math.floor(color));
        colorMag = colorMag%4;
        int response;
        if(colorMag == 1){
            response = R.color.magnitude1;
        }else if(colorMag==2){
            response = R.color.magnitude2;
        }else if(colorMag==3){
            response = R.color.magnitude3;
        }else if(colorMag==0){
            response = R.color.magnitude4;
        }else if(colorMag==5){
            response = R.color.magnitude5;
        }else if(colorMag==6){
            response = R.color.magnitude6;
        }else if(colorMag==7){
            response = R.color.magnitude7;
        }else if(colorMag==8){
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
