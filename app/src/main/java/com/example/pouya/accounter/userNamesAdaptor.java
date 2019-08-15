package com.example.pouya.accounter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by pouya on 10/12/2018.
 */

public class userNamesAdaptor extends ArrayAdapter<userNamesClass> {

    public userNamesAdaptor(@NonNull Context context, List<userNamesClass> userNameList) {
        super(context,0, userNameList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.personel_list_item,parent,false);
        }
        userNamesClass currentUser = getItem(position);

        TextView userNameID = (TextView) listItemView.findViewById(R.id.userID);
        GradientDrawable machine = (GradientDrawable) userNameID.getBackground();
        int idColor = getMagnitudeColor(Integer.parseInt(currentUser.getId()));
        machine.setColor(idColor);
        userNameID.setText(currentUser.getId());
        TextView userNameShow = (TextView) listItemView.findViewById(R.id.userShow);
        userNameShow.setText(currentUser.getUserName());
        TextView passwordShow  = (TextView) listItemView.findViewById(R.id.passwordShow);
        passwordShow.setText(currentUser.getPassword());

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
        }else{
            response = R.color.magnitude10plus;
        }
        return ContextCompat.getColor(getContext(),response);
    }
}
