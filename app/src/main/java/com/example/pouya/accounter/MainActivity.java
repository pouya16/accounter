package com.example.pouya.accounter;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText txtUser = (EditText) findViewById(R.id.txtUserName);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        Button btnEnter = (Button) findViewById(R.id.btnEnter);

        //request permission session
        requestForWriteSDCardPermission();
        G createClass = new G();
        try{
            createClass.createOrOpenDataBase(MainActivity.this);
            Toast.makeText(MainActivity.this,"Ready to Work",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"DataBase Didn't Load",Toast.LENGTH_SHORT).show();
        }

        fillPersonels(Constants.personelUsers,Constants.personelPasswords,Constants.personelID);


        //entering session
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = txtUser.getText().toString();
                String passWord = txtPassword.getText().toString();
                if(userName.equals(Constants.userName)){
                    if(passWord.equals(Constants.password)){
                        Intent intent = new Intent(MainActivity.this,shiftdateActivity.class);
                        Constants.enteredUser = "1";
                        txtPassword.setText("");
                        txtUser.setText("");
                        MainActivity.this.startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"password is not valid",Toast.LENGTH_LONG).show();
                        txtPassword.setText("");
                    }
                }
                else{
                    boolean flag = false;

                    for(int i = 0;i<Constants.personelUsers.size();i++){
                        if(userName.equals(Constants.personelUsers.get(i))){
                            if(passWord.equals(Constants.personelPasswords.get(i))){
                                flag = true;
                                Constants.enteredUser = Constants.personelID.get(i);
                            }
                            else{
                                Toast.makeText(MainActivity.this,"password is not valid",Toast.LENGTH_LONG).show();
                                txtPassword.setText("");
                            }
                        }
                    }
                    if(flag){
                        Intent intent = new Intent(MainActivity.this,shiftdateActivity.class);
                        txtPassword.setText("");
                        txtUser.setText("");
                        MainActivity.this.startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this,"user name or password is not valid",Toast.LENGTH_LONG).show();
                        txtPassword.setText("");
                    }
                    txtUser.setText("");
                    txtPassword.setText("");
                   //Intent intent = new Intent(MainActivity.this,testAcivity.class);
                   //MainActivity.this.startActivity(intent);

                }
            }
        });

    }
        //functions
        //request For Write Data function
    public void requestForWriteSDCardPermission(){
        RequestHelper request = new RequestHelper(this);
        RequestHelper.OnGrantedListener grantedListenerListener = new RequestHelper.OnGrantedListener() {
            @Override
            public void onGranted() {
            }
        };

        RequestHelper.OnAlreadyGrantedListener onAlreadyGrantedListener = new RequestHelper.OnAlreadyGrantedListener() {
            @Override
            public void onAlreadyGranted() {

            }
        };

        RequestHelper.OnDeniedListener deniedListener = new RequestHelper.OnDeniedListener() {
            @Override
            public void onDenied() {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Permission Required")
                        .setMessage("Writing to SDCARD required for this app")
                        .setPositiveButton("Ask me again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestForWriteSDCardPermission();
                            }
                        })
                        .create()
                        .show();
            }
        };
        request.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, grantedListenerListener, deniedListener);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        RequestHelper.onRequestPermissionResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public void fillPersonels(ArrayList<String> users,ArrayList<String> passwords,ArrayList<String> userID){
        String user1 = "";
        String password1="";
        int id = 0;
        int i = 0;

        String query = "SELECT * FROM users";
        try {
            Cursor cursor = G.database.rawQuery(query,null);
            while (cursor.moveToNext()){
                user1 = cursor.getString(2);
                password1 = cursor.getString(3);
                id = cursor.getInt(1);
                Log.i("Log0: ","user = "+ cursor.getString(2)+" password = " + cursor.getString(2));
                //users.add( cursor.getString(2));
                //passwords.add (cursor.getString(3));
                Log.i("Log1: ","user = "+ user1+" password = " + password1);
                //i++;
                users.add(user1);
                passwords.add(password1);
                userID.add(""+id);

                Toast.makeText(MainActivity.this,"Data Base has been Read",Toast.LENGTH_SHORT).show();

            }
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Some Things Wrong",Toast.LENGTH_SHORT).show();

        }


    }

}
