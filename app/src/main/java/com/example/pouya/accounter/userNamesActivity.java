package com.example.pouya.accounter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class userNamesActivity extends AppCompatActivity {
    Machines machines = new Machines();
    final ArrayList<userNamesClass> userNamesArray = new ArrayList<>();
    String deleteID ="";
    userNamesAdaptor adaptor;
    int adaptorPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_names_activity);
        ListView userNamesListView = (ListView) findViewById(R.id.userNamesListView);
        ImageButton btnUserNameAdd = (ImageButton) findViewById(R.id.addUserBtn);
        ImageButton btnUserNameUpdate = (ImageButton) findViewById(R.id.updateUserBtn);
        ImageButton btnUserNameDelete = (ImageButton) findViewById(R.id.deleteUserBtn);
        final EditText userEditTxt = (EditText) findViewById(R.id.userEditUserNames);
        final EditText passwordEditTxt = (EditText) findViewById(R.id.passwordEditUserNames);
        final EditText idEditTxt = (EditText) findViewById(R.id.idEditUserNames);
        final G createClass = new G();
        createClass.createOrOpenDataBase(userNamesActivity.this);
        try {
            createClass.createOrOpenDataBase(userNamesActivity.this);
            Toast.makeText(userNamesActivity.this, "برنامه آماده است.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(userNamesActivity.this, "ابتدا پوشه ها را ایجاد کنید", Toast.LENGTH_SHORT).show();
        }
        String userName = "";
        String password = "";
        String userID = "";
        // Log.i("Log0: ",""+"date: "+key+" Salon: "+salon+" txtMessage: "+report);
        try {

            Cursor curReport = G.database.rawQuery("SELECT * FROM users", null);
            while (curReport.moveToNext()) {
                Log.i("Log1: ", "key: " + curReport.getInt(1) + " report: " + curReport.getString(2));
                userID = "" + curReport.getInt(1);
                userName = curReport.getString(2);
                password = curReport.getString(3);
                Log.i("Log2: ", "" + "userId: " + userID + " userName: " + userName + " password: " + password);
                try {
                    //Log.i("Log3: ", "" + "date: " + key + " Salon: " + salon + " report: " + report);
                    userNamesClass usernameclass = new userNamesClass(userName, password, userID);
                    //Toast.makeText(reportActivity.this, "date: " + reportclass.getDate() + " Salon: " + reportclass.getShiftNumber(), Toast.LENGTH_SHORT).show();
                    Log.i("Log4: ", "" + "userClassUserID: " + usernameclass.getId() + " userClassUserName: " + usernameclass.getUserName());
                    userNamesArray.add(usernameclass);
                } catch (Exception e) {
                    Log.i("Log: ", "Fatal error" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.i("Log: ", "Fatal error" + e.getMessage());
        }
        adaptor = new userNamesAdaptor(this, userNamesArray);
        userNamesListView.setAdapter(adaptor);


        userNamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                userNamesClass selectedUser = adaptor.getItem(position);
                userEditTxt.setText(selectedUser.getUserName());
                adaptorPosition = position;
                passwordEditTxt.setText(selectedUser.getPassword());
                deleteID = selectedUser.getId();
                idEditTxt.setText(selectedUser.getId());
            }
        });

        btnUserNameUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(userEditTxt,passwordEditTxt,idEditTxt);
                if(!emptyFlag){
                    Toast.makeText(userNamesActivity.this,"نام کاربری و رمز درست را وارد کنید",Toast.LENGTH_SHORT).show();

                }
                try {
                    int key = Integer.parseInt(idEditTxt.getText().toString());
                    String updateQuery = "UPDATE users SET userName = '" + userEditTxt.getText().toString() + "', password = '" + passwordEditTxt.getText().toString() + "' WHERE userId = " + key;
                    G.database.execSQL(updateQuery);
                    Toast.makeText(userNamesActivity.this,"نام کاربری و رمز عبور با موفقیت بازنشانی شد",Toast.LENGTH_SHORT).show();
                    makeReset(userEditTxt,passwordEditTxt,idEditTxt);
                }catch (Exception e){
                    Log.i("Log update: ", "update Failed");
                }
            }
        });

        btnUserNameDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int key = -1;
                try {
                    if(deleteID!=null) {
                        key = Integer.parseInt(deleteID);
                    }else{
                        Toast.makeText(userNamesActivity.this,"یک یوزر را انتخاب کنید",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                }
                if(key != -1){
                    try {
                        String deleteQuery = "DELETE FROM users WHERE userId=" + key;
                        G.database.execSQL(deleteQuery);
                        makeReset(userEditTxt,passwordEditTxt,idEditTxt);
                        if(adaptorPosition!=-1){
                            userNamesClass currentUserName = adaptor.getItem(adaptorPosition);
                            adaptor.remove(currentUserName);
                            adaptor.notifyDataSetChanged();
                        }
                        adaptorPosition = -1;
                        Toast.makeText(userNamesActivity.this,"کاربر با موفقیت حذف شد.",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Log.i("Log Delete: ", "ِ Delete Failed");
                        Toast.makeText(userNamesActivity.this,"حذف انجام نشد.",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        btnUserNameAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(userEditTxt,passwordEditTxt,idEditTxt);
                if(!emptyFlag){
                    Toast.makeText(userNamesActivity.this,"مقدارها را درست وارد کنید",Toast.LENGTH_SHORT).show();
                }else{
                    postUser(G.database,idEditTxt.getText().toString(),userEditTxt.getText().toString(),passwordEditTxt.getText().toString());
                    Toast.makeText(userNamesActivity.this,"نام کاربری با موفقیت وارد سیستم شد.",Toast.LENGTH_SHORT).show();
                    userNamesClass currentUserName = new userNamesClass(userEditTxt.getText().toString(),passwordEditTxt.getText().toString(),idEditTxt.getText().toString());
                    adaptor.add(currentUserName);
                    adaptor.notifyDataSetChanged();
                    makeReset(userEditTxt,passwordEditTxt,idEditTxt);
                }
            }
        });


    }
    public boolean checkNotEmpty(EditText txt1,EditText txt2,EditText txt3){
        if(txt1.getText().toString().length()<4){
            return false;
        }if(txt2.getText().toString().length()<4){
            return false;
        }
        if(txt3.getText().toString().length()<1){
            return false;
        }
        return true;
    }
    public void postUser(SQLiteDatabase db, String userID, String userName, String password){
        try {
            int userid = Integer.parseInt(userID);
            String query = "INSERT INTO 'users'('userId','userName','password') values " +
                    "('" + userid + "', '" + userName + "', '"+ password + "')";
            db.execSQL(query);
            Log.i("Log", "user successfully added");
        }catch (Exception e){
            Log.e("Log",""+e.getMessage());
            Log.i("Log", "Failed to add user");
        }
    }
    public void makeReset(EditText txt1,EditText txt2,EditText txt3){
        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
    }

}
