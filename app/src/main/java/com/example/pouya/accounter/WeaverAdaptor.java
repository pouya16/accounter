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

public class WeaverAdaptor extends ArrayAdapter<WeaverClass> {

    public WeaverAdaptor(@NonNull Context context, List<WeaverClass> weaverList) {
        super(context,0, weaverList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.weaver_list_item,parent,false);
        }
        WeaverClass currentWeaver = getItem(position);

        TextView weaverID = (TextView) listItemView.findViewById(R.id.weaverPNumber);
        GradientDrawable machine = (GradientDrawable) weaverID.getBackground();
        int idColor = getMagnitudeColor(Integer.parseInt(currentWeaver.getWeaverPesonelID()));
        machine.setColor(idColor);
        weaverID.setText(currentWeaver.getWeaverPesonelID());
        TextView weaverIDShow = (TextView) listItemView.findViewById(R.id.weaverPersonelNumber);
        weaverIDShow.setText(currentWeaver.getWeaverPesonelID());
        TextView weaverName  = (TextView) listItemView.findViewById(R.id.weaverName);
        weaverName.setText(currentWeaver.getWeaverName());

        return listItemView;

    }
    private int getMagnitudeColor(double color){
        int colorMag = ((int) Math.floor(color));
        colorMag = colorMag%10;
        int response;
        if(colorMag == 1){
            response = R.color.magnitude1;
        }else if(colorMag==2){
            response = R.color.magnitude2;
        }else if(colorMag==3){
            response = R.color.magnitude3;
        }else if(colorMag==4){
            response = R.color.magnitude4;
        }else if(colorMag==5){
            response = R.color.magnitude5;
        }else if(colorMag==6){
            response = R.color.magnitude6;
        }else if(colorMag==7){
            response = R.color.magnitude7;
        }else if(colorMag==8){
            response = R.color.magnitude8;
        }else if(colorMag==9) {
            response = R.color.magnitude9;
        }else{
            response = R.color.magnitude10plus;
        }
        Log.i("Response Log","Our absolute Mag is: "+color);
        Log.i("Response Log","Our int color: "+colorMag);
        Log.i("Response Log","Our response is: "+response);
        return ContextCompat.getColor(getContext(),response);
    }
}
