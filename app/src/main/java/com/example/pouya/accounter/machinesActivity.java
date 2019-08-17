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

public class machinesActivity extends AppCompatActivity {
    Machines machines = new Machines();
    final ArrayList<machineClass> machinesArray = new ArrayList<>();
    String deleteMachineNumber = "";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machines);
        ListView machinesListView = (ListView) findViewById(R.id.machinesListView);
        ImageButton btnMachineAdd = (ImageButton) findViewById(R.id.addMachineBtn);
        ImageButton btnMachineUpdate = (ImageButton) findViewById(R.id.updateMachineBtn);
        ImageButton btnMachineDelete = (ImageButton) findViewById(R.id.deleteMachineBtn);
        final EditText machineEditTxt = (EditText) findViewById(R.id.machineEnterEditText);
        final EditText salonEditTxt = (EditText) findViewById(R.id.salonEnterEditText);

        machineEditTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                machineEditTxt.setText("");
            }
        });
        salonEditTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salonEditTxt.setText("");
            }
        });

        final G createClass = new G();
        try{
            createClass.createOrOpenDataBase(machinesActivity.this);
            Toast.makeText(machinesActivity.this,"برنامه آماده است.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(machinesActivity.this,"ابتدا پوشه ها را ایجاد کنید",Toast.LENGTH_SHORT).show();
        }
        String machineNumber = "";
        String salonNumber = "";

        try {

            Cursor curMachines = G.database.rawQuery("SELECT * FROM machines", null);
            while (curMachines.moveToNext()) {
                Log.i("Log1: ", "machineNumber: " + curMachines.getInt(1) + " salonNumber: " + curMachines.getString(2));
                machineNumber = "" + curMachines.getInt(1);
                salonNumber = "" + curMachines.getInt(2);
                Log.i("Log2: ", "" + "machineNumber: " + machineNumber + " salonNumber: " + salonNumber);
                try {
                    //Log.i("Log3: ", "" + "date: " + key + " Salon: " + salon + " report: " + report);
                    machineClass machinesclass = new machineClass(machineNumber,salonNumber);
                    //Toast.makeText(reportActivity.this, "date: " + reportclass.getDate() + " Salon: " + reportclass.getShiftNumber(), Toast.LENGTH_SHORT).show();
                    Log.i("Log4: ", "" + "machineNumber: " + machinesclass.getMachineNumber() + " salonNumber: " + machinesclass.getSalonNumber());
                    machinesArray.add(machinesclass);
                } catch (Exception e) {
                    Log.i("Log: ", "Fatal error" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.i("Log: ", "Fatal error" + e.getMessage());
        }
        final machinesAdaptor adaptor = new machinesAdaptor(this, machinesArray);
        machinesListView.setAdapter(adaptor);

        machinesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                machineClass currentmachine = adaptor.getItem(position);
                machineEditTxt.setText(currentmachine.getMachineNumber());
                deleteMachineNumber = currentmachine.getMachineNumber();
                salonEditTxt.setText(currentmachine.getSalonNumber());
            }
        });
        btnMachineUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(machineEditTxt,salonEditTxt);
                if(!emptyFlag){
                    Toast.makeText(machinesActivity.this,"شماره ماشین و شماره سالن را صحیح وارد کنید",Toast.LENGTH_SHORT).show();

                }
                try {
                    int key = Integer.parseInt(machineEditTxt.getText().toString());
                    String updateQuery = "UPDATE machines SET salonNumber = '" + salonEditTxt.getText().toString()+ "' WHERE machineNumber = " + key;
                    G.database.execSQL(updateQuery);
                    Toast.makeText(machinesActivity.this,"نام کاربری و رمز عبور با موفقیت بازنشانی شد",Toast.LENGTH_SHORT).show();
                    makeReset(machineEditTxt,salonEditTxt);
                }catch (Exception e){
                    Log.i("Log update: ", "update Failed");
                }
            }
        });

        btnMachineDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(machineEditTxt,salonEditTxt);
                if(!emptyFlag){
                    Toast.makeText(machinesActivity.this,"شماره ماشین و شماره سالن را صحیح وارد کنید",Toast.LENGTH_SHORT).show();
                }else {
                    int key = -1;
                    try {
                        if(deleteMachineNumber!=null){
                            key = Integer.parseInt(deleteMachineNumber);
                        }else{
                            Toast.makeText(machinesActivity.this,"یک ماشین را انتخاب کنید.",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {

                    }
                    if (key != -1) {
                        try {
                            String deleteQuery = "DELETE FROM machines WHERE machineNumber=" + key;
                            G.database.execSQL(deleteQuery);
                            makeReset(machineEditTxt,salonEditTxt);
                            Toast.makeText(machinesActivity.this, "ماشین با موفقیت حذف شد.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Log.i("Log Delete: ", "ِ Delete Failed");
                            Toast.makeText(machinesActivity.this, "حذف انجام نشد.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });


        btnMachineAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean emptyFlag = checkNotEmpty(machineEditTxt,salonEditTxt);
                if(!emptyFlag){
                    Toast.makeText(machinesActivity.this,"شماره ماشین و شماره سالن را صحیح وارد کنید",Toast.LENGTH_SHORT).show();
                }else{
                    postMachine(G.database,machineEditTxt.getText().toString(),salonEditTxt.getText().toString());
                    Toast.makeText(machinesActivity.this,"ماشین با موفقیت وارد سیستم شد.",Toast.LENGTH_SHORT).show();
                    makeReset(machineEditTxt,salonEditTxt);
                }
            }
        });



    }
    public boolean checkNotEmpty(EditText txt1,EditText txt2){
        if(txt1.getText().toString().length()<2){
            return false;
        }if(txt2.getText().toString().length()<1){
            return false;
        }
        return true;
    }
    public void postMachine(SQLiteDatabase db, String machineNumber, String salonNumber){
        try {
            int machine = Integer.parseInt(machineNumber);
            int salon = Integer.parseInt(salonNumber);
            String query = "INSERT INTO 'machines'('machineNumber','salonNumber') values " +
                    "('" + machine + "', '" + salon + "')";
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
