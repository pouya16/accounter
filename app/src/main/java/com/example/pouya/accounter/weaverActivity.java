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

public class weaverActivity extends AppCompatActivity {

    Machines machines = new Machines();
    final ArrayList<WeaverClass> weaverArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weaver);
        ListView veawerListView = (ListView) findViewById(R.id.weaverListView);
        ImageButton btnWeaverAdd = (ImageButton) findViewById(R.id.addWeaverBtn);
        ImageButton btnWeaverUpdate = (ImageButton) findViewById(R.id.updateWeaverBtn);
        ImageButton btnWeaverDelete = (ImageButton) findViewById(R.id.deleteWeaver);
        final EditText veawerNameEditTxt = (EditText) findViewById(R.id.weaverEnterEditText);
        final EditText veawerIDEditTxt = (EditText) findViewById(R.id.weaverEnterID);

        final G createClass = new G();
        try{
            createClass.createOrOpenDataBase(weaverActivity.this);
            Toast.makeText(weaverActivity.this,"برنامه آماده است.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(weaverActivity.this,"ابتدا پوشه ها را ایجاد کنید",Toast.LENGTH_SHORT).show();
        }
        String weaverName = "";
        String weaverPersonelID = "";

        try {

            Cursor curWeavers = G.database.rawQuery("SELECT * FROM weavers", null);
            while (curWeavers.moveToNext()) {
                Log.i("Log1: ", "WeaverName: " + curWeavers.getInt(2) + " weaverID: " + curWeavers.getString(1));
                weaverPersonelID = "" + curWeavers.getInt(1);
                weaverName =  curWeavers.getString(2);
                Log.i("Log2: ", "" + "Weaver Name: " + weaverName + " WeaverNumber: " + weaverPersonelID);
                try {
                    //Log.i("Log3: ", "" + "date: " + key + " Salon: " + salon + " report: " + report);
                    WeaverClass weaverClass = new WeaverClass(weaverName,weaverPersonelID);
                    //Toast.makeText(reportActivity.this, "date: " + reportclass.getDate() + " Salon: " + reportclass.getShiftNumber(), Toast.LENGTH_SHORT).show();
                    Log.i("Log4: ", "" + "weaver name: " + weaverClass.getWeaverName() + " weaverPersonleID: " + weaverClass.getWeaverPesonelID());
                    weaverArray.add(weaverClass);
                } catch (Exception e) {
                    Log.i("Log: ", "Fatal error" + e.getMessage());
                }
            }
            curWeavers.close();
        } catch (Exception e) {
            Log.i("Log: ", "Fatal error" + e.getMessage());
        }
        final WeaverAdaptor adaptor = new WeaverAdaptor(this, weaverArray);
        veawerListView.setAdapter(adaptor);

        veawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                WeaverClass currentWeaver = adaptor.getItem(position);
                veawerNameEditTxt.setText(currentWeaver.getWeaverName());
                veawerIDEditTxt.setText(currentWeaver.getWeaverPesonelID());
            }
        });
        /*btnWeaverUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(veawerNameEditTxt,veawerIDEditTxt);
                if(!emptyFlag){
                    Toast.makeText(weaverActivity.this,"شماره ماشین و شماره سالن را صحیح وارد کنید",Toast.LENGTH_SHORT).show();

                }
                try {
                    int key = Integer.parseInt(veawerIDEditTxt.getText().toString());
                    String updateQuery = "UPDATE veawers SET weaverName = '" + salonEditTxt.getText().toString()+ "' WHERE machineNumber = " + key;
                    G.database.execSQL(updateQuery);
                    Toast.makeText(machinesActivity.this,"نام کاربری و رمز عبور با موفقیت بازنشانی شد",Toast.LENGTH_SHORT).show();
                    makeReset(machineEditTxt,salonEditTxt);
                }catch (Exception e){
                    Log.i("Log update: ", "update Failed");
                }
            }
        });*/

        btnWeaverDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(veawerNameEditTxt,veawerIDEditTxt);
                if(!emptyFlag){
                    Toast.makeText(weaverActivity.this,"نام بافنده و کد کاربری را صحیح وارد کنید",Toast.LENGTH_SHORT).show();
                }else {
                    int key = -1;
                    try {
                        key = Integer.parseInt(veawerIDEditTxt.getText().toString());
                    } catch (Exception e) {

                    }
                    if (key != -1) {
                        try {
                            String deleteQuery = "DELETE FROM weavers WHERE weaverID=" + key;
                            G.database.execSQL(deleteQuery);
                            makeReset(veawerNameEditTxt,veawerIDEditTxt);
                            Toast.makeText(weaverActivity.this, "بافنده با موفقیت حذف شد.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.i("Log Delete: ", "ِ Delete Failed");
                            Toast.makeText(weaverActivity.this, "حذف انجام نشد.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        btnWeaverAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(veawerNameEditTxt,veawerIDEditTxt);
                if(!emptyFlag){
                    Toast.makeText(weaverActivity.this,"نام بافنده و کد پرسنلی را صحیح وارد کنید",Toast.LENGTH_SHORT).show();
                }else{
                    postWever(G.database,veawerIDEditTxt.getText().toString(),veawerNameEditTxt.getText().toString());
                    Toast.makeText(weaverActivity.this,"بافنده با موفقیت وارد سیستم شد.",Toast.LENGTH_SHORT).show();
                    makeReset(veawerNameEditTxt,veawerIDEditTxt);
                }
            }
        });
    }




    public boolean checkNotEmpty(EditText txt1, EditText txt2){
        if(txt1.getText().toString().length()<2){
            return false;
        }if(txt2.getText().toString().length()<1){
            return false;
        }
        return true;
    }
    public void postWever(SQLiteDatabase db, String weaverID, String weaverName){
        try {
            int id = Integer.parseInt(weaverID);
            String query = "INSERT INTO 'weavers'('weaverID','weaverName') values " +
                    "('" + id + "', '" + weaverName + "')";
            db.execSQL(query);
            Log.i("Log", "user successfully added");
        }catch (Exception e){
            Log.e("Log",""+e.getMessage());
            Log.i("Log", "Failed to add user");
        }
    }
    public void makeReset(EditText txt1,EditText txt2){
        txt1.setText("");
        txt2.setText("");
    }
}
