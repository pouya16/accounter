package com.example.pouya.accounter;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class testAcivity extends AppCompatActivity {
    Machines machines = new Machines();
    workingClass scrollshift;
    protected  void onResume() {
        super.onResume();
        Button btnSQLMaker = (Button) findViewById(R.id.btnSQL);
        Button btnReserve = (Button) findViewById(R.id.btnReserve);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnShiftPrint = (Button) findViewById(R.id.btnPrintShift);
        Button btnShiftRead = (Button) findViewById(R.id.btnShiftRead);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final TextView txtDate = (TextView) findViewById(R.id.txtDateView);
        final EditText txtMachineNumber = (EditText) findViewById(R.id.txtMachineNumber);
        final EditText txtOldMeter = (EditText) findViewById(R.id.txtOldMeter);
        final EditText txtOldMeterAdmin = (EditText) findViewById(R.id.txtOldMeter);
        final EditText txtWeaveType = (EditText) findViewById(R.id.txtWeaveType);
        final EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        final EditText txtExtra = (EditText) findViewById(R.id.txtExtra);
        TextView txtPersonId = (TextView) findViewById(R.id.txtPersonelName);
        final EditText txtUserID = (EditText) findViewById(R.id.txtUserID);
        Spinner personSpinner = (Spinner) findViewById(R.id.prsnlNameSpinner);
        final G createClass = new G();
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final ArrayList<workingClass> scrolledShift = new ArrayList<workingClass>();
        txtPersonId.setText(Constants.enteredUser);
        txtOldMeter.setKeyListener(null);
        if(Integer.parseInt(Constants.enteredUser) != 1 )
        {
            txtOldMeterAdmin.setKeyListener(null);
        }
        try{
            createClass.createOrOpenDataBase(testAcivity.this);
            Toast.makeText(testAcivity.this,"برنامه آماده است.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(testAcivity.this,"ابتدا پوشه ها را ایجاد کنید",Toast.LENGTH_SHORT).show();
        }


        try {
            String weaversQuery = "SELECT * FROM weavers ORDER BY weaverName ASC";
            Cursor weaverCur = G.database.rawQuery(weaversQuery, null);
            String weaver[] = new String[weaverCur.getCount()];
            final String weaverID[] = new String[weaverCur.getCount()];
            int weaverFakeID = 0;
            int weaverIndex = 0;
            while (weaverCur.moveToNext()) {
                weaver[weaverIndex] = weaverCur.getString(2);
                weaverFakeID = (weaverCur.getInt(1));
                weaverID[weaverIndex] = "" + weaverFakeID;
                weaverIndex++;
            }
            weaverIndex = 0;
            Log.i("Weaver Log0: ", "Count: " + weaverCur.getCount());
            while (weaverIndex < weaverCur.getCount()) {
                Log.i("Weaver Log1: ", "weaver" + weaverIndex + ": " + weaver[weaverIndex]);
                weaverIndex++;
            }
            weaverCur.close();

            ArrayAdapter<String> personAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, weaver);
            personSpinner.setAdapter(personAdaptor);
            personSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    txtUserID.setText(weaverID[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    machines.day = "1";
                    machines.dateString = machines.year + machines.month + machines.day;
                    txtDate.setText(machines.dateString);

                }
            });
        }catch (Exception e){
            Toast.makeText(testAcivity.this,"Something Wrong",Toast.LENGTH_SHORT).show();
        }
        machines.date = getIntent().getIntExtra("machinesDate",10);
        machines.salonNumber = getIntent().getIntExtra("machinesSalon",0);
        machines.shiftNumber = getIntent().getIntExtra("machinesShift",0);





        txtDate.setText(""+machines.date);


        //buttons conficuration
        //buttons conficuration
        btnSQLMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClass.createOrOpenDataBase(testAcivity.this);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_acivity);
        Button btnSQLMaker = (Button) findViewById(R.id.btnSQL);
        Button btnReserve = (Button) findViewById(R.id.btnReserve);
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        Button btnBack = (Button) findViewById(R.id.btnBack);
        Button btnNext = (Button) findViewById(R.id.btnNext);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnShiftPrint = (Button) findViewById(R.id.btnPrintShift);
        Button btnShiftRead = (Button) findViewById(R.id.btnShiftRead);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdate);
        final TextView txtDate = (TextView) findViewById(R.id.txtDateView);
        final EditText txtMachineNumber = (EditText) findViewById(R.id.txtMachineNumber);
        final EditText txtOldMeter = (EditText) findViewById(R.id.txtOldMeter);
        final EditText txtOldMeterAdmin = (EditText) findViewById(R.id.txtOldMeterAdmin);
        final EditText txtNewMeter = (EditText) findViewById(R.id.txtNewMeter);
        final EditText txtRing = (EditText) findViewById(R.id.txtRing);
        final EditText txtWeaveType = (EditText) findViewById(R.id.txtWeaveType);
        final EditText txtUserName = (EditText) findViewById(R.id.txtUserName);
        final EditText txtExtra = (EditText) findViewById(R.id.txtExtra);
        TextView txtPersonId = (TextView) findViewById(R.id.txtPersonelName);
        final EditText txtZeroMeter = (EditText) findViewById(R.id.txtZeroMeter);
        final EditText txtUserID = (EditText) findViewById(R.id.txtUserID);
        Spinner personSpinner = (Spinner) findViewById(R.id.prsnlNameSpinner);
        final G createClass = new G();
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final ArrayList<workingClass> scrolledShift = new ArrayList<workingClass>();

        //defaults
        txtPersonId.setText(Constants.enteredUser);
        txtOldMeter.setKeyListener(null);
        if(Integer.parseInt(Constants.enteredUser) != 1 )
        {
            txtOldMeterAdmin.setKeyListener(null);
        }
        try{
            createClass.createAppDirectories(testAcivity.this);
            createClass.createOrOpenDataBase(testAcivity.this);
            Toast.makeText(testAcivity.this,"برنامه آماده است.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(testAcivity.this,"ابتدا پوشه ها را ایجاد کنید",Toast.LENGTH_SHORT).show();
        }


        try {
            String weaversQuery = "SELECT * FROM weavers ORDER BY weaverName ASC";
            Cursor weaverCur = G.database.rawQuery(weaversQuery, null);
            final String weaver[] = new String[weaverCur.getCount()];
            final String weaverID[] = new String[weaverCur.getCount()];
            int weaverFakeID = 0;
            int weaverIndex = 0;
            while (weaverCur.moveToNext()) {
                weaver[weaverIndex] = weaverCur.getString(2);
                weaverFakeID = (weaverCur.getInt(1));
                weaverID[weaverIndex] = "" + weaverFakeID;
                weaverIndex++;
            }
            weaverIndex = 0;
            Log.i("Weaver Log0: ", "Count: " + weaverCur.getCount());
            while (weaverIndex < weaverCur.getCount()) {
                Log.i("Weaver Log1: ", "weaver" + weaverIndex + ": " + weaver[weaverIndex]);
                weaverIndex++;
            }
            weaverCur.close();

            ArrayAdapter<String> personAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, weaver);
            personSpinner.setAdapter(personAdaptor);
            personSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    txtUserID.setText(weaverID[position]);
                    txtUserName.setText(weaver[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    machines.day = "1";
                    machines.dateString = machines.year + machines.month + machines.day;
                    txtDate.setText(machines.dateString);

                }
            });
        }catch (Exception e){
            Toast.makeText(testAcivity.this,"Something Wrong",Toast.LENGTH_SHORT).show();
        }
        machines.date = getIntent().getIntExtra("machinesDate",10);
        machines.salonNumber = getIntent().getIntExtra("machinesSalon",0);
        machines.shiftNumber = getIntent().getIntExtra("machinesShift",0);





        txtDate.setText(""+machines.date);


        //buttons configuration
        //buttons configuration
        btnSQLMaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createClass.createOrOpenDataBase(testAcivity.this);
            }
        });
        btnReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.enteredUser.equals(Constants.adminUserID)) {
                    Intent intent = new Intent(testAcivity.this, adminActivity.class);
                    testAcivity.this.startActivity(intent);
                } else {
                    Toast.makeText(testAcivity.this, "برای ورود به این صفحه نیاز به یوز ادمین است", Toast.LENGTH_LONG).show();
                }
            }
        });




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (machines.isChanging == true) {
                        txtNewMeter.setText("");
                        txtOldMeter.setText("");
                        txtRing.setText("");
                        txtUserID.setText("");
                        txtUserName.setText("");
                        txtExtra.setText("");
                        machines.autoID = 0;
                        machines.isChanging = false;
                        machines.isInserted = true;
                    } else {
                        boolean isEmpty = checkNotEmpty(txtNewMeter, txtDate, txtMachineNumber);
                        boolean isMachineExist = checkMachine(txtMachineNumber);
                        if (isEmpty) {
                            if (isMachineExist) {
                                int checked = 0;
                                if (checkBox.isChecked()) {
                                    checked = 1;
                                } else {
                                    checked = 0;
                                }
                                setMachines(txtMachineNumber, txtNewMeter, txtRing, txtZeroMeter, txtUserID, txtWeaveType, txtExtra);
                                calculateKey(txtMachineNumber, machines.date);
                                Toast.makeText(testAcivity.this, "key is: " + machines.key, Toast.LENGTH_SHORT).show();
                                if (txtOldMeterAdmin.getText().length() < 1) {
                                    machines.oldMeter = getOldMeter(machines.machineNumber, machines.salonNumber);
                                    txtOldMeter.setText("" + machines.oldMeter);
                                } else {
                                    machines.oldMeter = Integer.parseInt(txtOldMeterAdmin.getText().toString());
                                    txtOldMeter.setText(txtOldMeterAdmin.getText().toString());
                                }
                                machines.metrazh = calculateMetrazh(machines.oldMeter, machines.newMeter, machines.zeroMeter);
                                final int finalChecked = checked;
                                new AlertDialog.Builder(testAcivity.this).setTitle("تایید متراژ").setMessage("آیا متراژ بافت زیر مورد تایید است؟ " + "\n" + machines.metrazh)
                                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (txtZeroMeter.toString().equals("")) {
                                                    insertToMachines(machines.date, machines.shiftNumber, machines.salonNumber, machines.machineNumber, machines.oldMeter, machines.newMeter, machines.metrazh, machines.ring, finalChecked, machines.personId, machines.key, machines.userID, machines.weaveType,machines.extra);
                                                } else {
                                                    insertToMachines(machines.date, machines.shiftNumber, machines.salonNumber, machines.machineNumber, machines.oldMeter, machines.newMeter, machines.metrazh, machines.ring, finalChecked, machines.personId, machines.key, machines.userID, machines.weaveType,machines.extra);
                                                }
                                                Log.i("Log", "Our Job is successfully ended");
                                                if (getAutoID(machines.key) != -1) {
                                                    machines.autoID = getAutoID(machines.key);
                                                }
                                                Toast.makeText(testAcivity.this, "" + machines.autoID, Toast.LENGTH_SHORT).show();
                                                txtMachineNumber.setText("" + (machines.machineNumber + 1));
                                                txtNewMeter.setText("");
                                                txtOldMeter.setText("");
                                                txtOldMeterAdmin.setText("");
                                                txtWeaveType.setText("");
                                                txtRing.setText("");
                                                txtExtra.setText("");
                                                machines.autoID = 0;
                                                machines.isChanging = false;
                                                machines.isInserted = true;
                                            }
                                        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                }).create().show();

                            } else {
                                Toast.makeText(testAcivity.this, "ماشین وجود ندارد", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(testAcivity.this, "باید همه قسمت ها را پر کنید", Toast.LENGTH_LONG).show();
                        }
                    }

                }catch(Exception e){
                            Toast.makeText(testAcivity.this, "اطلاعات ناقص است.", Toast.LENGTH_LONG).show();
                            Log.i("Log","eshkal" + e.toString());

            }

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isEmpty = checkNotEmpty(txtNewMeter, txtDate, txtMachineNumber);
                boolean isMachineExist = checkMachine(txtMachineNumber);
                if (isEmpty) {
                    if(isMachineExist) {
                        int checked = 0;
                        if (checkBox.isChecked()) {
                            checked = 1;
                        } else {
                            checked = 0;
                        }
                        setMachines(txtMachineNumber, txtNewMeter, txtRing, txtZeroMeter, txtUserID,txtWeaveType,txtExtra);
                        calculateKey(txtMachineNumber, machines.date);
                        Toast.makeText(testAcivity.this, "key is: " + machines.key, Toast.LENGTH_SHORT).show();
                        machines.metrazh = calculateMetrazh(machines.oldMeter, machines.newMeter, machines.zeroMeter);
                        final int finalChecked = checked;
                        machines.oldMeter = Integer.parseInt(txtOldMeter.getText().toString());
                        new AlertDialog.Builder(testAcivity.this).setTitle("تایید متراژ").setMessage("آیا متراژ بافت زیر مورد تایید است؟ "+"\n"+machines.metrazh)
                                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (txtZeroMeter.toString().equals("")) {
                                            updateMachine(machines.date, machines.shiftNumber, machines.salonNumber, machines.machineNumber, machines.oldMeter, machines.newMeter, machines.metrazh, machines.ring, finalChecked, machines.personId, machines.key, machines.userID,machines.extra);
                                        } else {
                                            updateMachine(machines.date, machines.shiftNumber, machines.salonNumber, machines.machineNumber, machines.oldMeter, machines.newMeter, machines.metrazh, machines.ring, finalChecked, machines.personId, machines.key, machines.userID,machines.extra);
                                        }
                                        Log.i("Log", "Our Job is successfully ended");
                                        if (getAutoID(machines.key) != -1) {
                                            machines.autoID = getAutoID(machines.key);
                                        }
                                        Toast.makeText(testAcivity.this, "" + machines.autoID, Toast.LENGTH_SHORT).show();
                                        txtMachineNumber.setText("" + (machines.machineNumber + 1));
                                        txtNewMeter.setText("");
                                        txtOldMeter.setText("");
                                        txtExtra.setText("");
                                        machines.autoID = 0;
                                        machines.isChanging = false;
                                        machines.isInserted = true;
                                    }
                                }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();

                    }else{
                        Toast.makeText(testAcivity.this, "ماشین وجود ندارد", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(testAcivity.this, "باید همه قسمت ها را پر کنید", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(machines.arrayIndex<0||machines.arrayIndex>=(scrolledShift.size()-1)){
                    Toast.makeText(testAcivity.this,"اطلاعات بعدی وجود ندارد.",Toast.LENGTH_SHORT).show();
                }else{
                    machines.arrayIndex++;
                    txtMachineNumber.setText(scrolledShift.get(machines.arrayIndex).getMachineNumber());
                    txtNewMeter.setText(scrolledShift.get(machines.arrayIndex).getNewMeter());
                    txtOldMeter.setText(scrolledShift.get(machines.arrayIndex).getOldMeter());
                    txtUserID.setText(scrolledShift.get(machines.arrayIndex).getPersonID());
                    txtRing.setText(scrolledShift.get(machines.arrayIndex).getRing());
                    txtWeaveType.setText(scrolledShift.get(machines.arrayIndex).getWeaveType());
                    //txtExtra.setText(scrolledShift.get(machines.arrayIndex).getExtra());

                    txtUserName.setText(calculateUserName(scrolledShift.get(machines.arrayIndex).getPersonID()));


                }

                }
           //    else{
           //        Toast.makeText(testAcivity.this,"آیتم بعدی وجود ندارد",Toast.LENGTH_LONG).show();
           //    }
           //}
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(machines.arrayIndex<1){
                    Toast.makeText(testAcivity.this,"اطلاعات قبلی وجود ندارد.",Toast.LENGTH_SHORT).show();
                }else{
                    machines.arrayIndex--;
                    txtMachineNumber.setText(scrolledShift.get(machines.arrayIndex).getMachineNumber());
                    txtNewMeter.setText(scrolledShift.get(machines.arrayIndex).getNewMeter());
                    txtOldMeter.setText(scrolledShift.get(machines.arrayIndex).getOldMeter());
                    txtUserID.setText(scrolledShift.get(machines.arrayIndex).getPersonID());
                    txtRing.setText(scrolledShift.get(machines.arrayIndex).getRing());
                    txtWeaveType.setText(scrolledShift.get(machines.arrayIndex).getWeaveType());
                    //txtExtra.setText(scrolledShift.get(machines.arrayIndex).getExtra());
                    txtUserName.setText(calculateUserName(scrolledShift.get(machines.arrayIndex).getPersonID()));
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(machines.autoID==0){
                    Toast.makeText(testAcivity.this,"آیتمی برای پاک شدن انتخاب نشده",Toast.LENGTH_LONG).show();
                }
                else{
                    String query2 = "DELETE FROM metrazh WHERE autoId="+machines.autoID;
                    try{
                        G.database.execSQL(query2);
                        Toast.makeText(testAcivity.this,"با موفقیت حذف شد.",Toast.LENGTH_LONG).show();
                    }
                    catch (Exception e){
                        Toast.makeText(testAcivity.this,"ایراد کوئری",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        //btn RESET CLICK EVENT
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                machines.autoID = 0;
                txtMachineNumber.setText("");
                txtNewMeter.setText("");
                txtOldMeter.setText("");
                txtRing.setText("");
                txtExtra.setText("");
                machines.isInserted = false;
                machines.isChanging = false;
            }
        });


        btnShiftPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(testAcivity.this, WorkingActivity.class);
                testAcivity.this.startActivity(intent);

            }
        });


        btnShiftRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    machines.isInserted = generateViewShift(scrolledShift);
                    if (machines.isInserted) {
                        txtMachineNumber.setText(scrolledShift.get(0).getMachineNumber());
                        txtNewMeter.setText(scrolledShift.get(0).getNewMeter());
                        txtOldMeter.setText(scrolledShift.get(0).getOldMeter());
                        txtUserID.setText(scrolledShift.get(0).getPersonID());
                        txtRing.setText(scrolledShift.get(0).getRing());
                        txtWeaveType.setText(scrolledShift.get(0).getWeaveType());
                        //txtExtra.setText(scrolledShift.get(0).getExtra());
                        txtUserName.setText(calculateUserName(scrolledShift.get(0).getPersonID()));
                        machines.arrayIndex = 0;
                    } else {
                        Toast.makeText(testAcivity.this, "اطلاعات شیفت پیدا نشد", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                        Toast.makeText(testAcivity.this, "اطلاعات شیفت پیدا نشد", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }



    //EXTERNAL functions parts:
    //EXTERNAL functions parts:
    //EXTERNAL functions parts:
    //EXTERNAL functions parts:

    //INSERT FUNCTIONS:
    public boolean isSearchable(TextView txtDate,EditText txtPersonId,EditText txtMachineNumber){
        boolean searchable = false;
        if (txtDate.getText().toString().length() > 0) {
            Log.i("Log", "Date is not empty");
            if (txtPersonId.getText().toString().length() > 0) {
                if (txtMachineNumber.getText().toString().length() > 0) {
                    Log.i("Log", "Machine number is not empty");
                    searchable=true;
                }
            }
        }
        return searchable;
    }

    //calculateUserName
    public String calculateUserName(String userID){
        int user = Integer.parseInt(userID);
        String name = "";
        try {
            String weaversQuery = "SELECT * FROM weavers WHERE weaverID = '" + user+"'";
            Cursor weaverCur = G.database.rawQuery(weaversQuery, null);
            weaverCur.moveToFirst();
            name = weaverCur.getString(2);
            return name;
        }
        catch (Exception e){
            return name;
        }
    }

    public void insertToMachines(int date, int shiftNumber, int salonNumber, int machineNumber, int oldMeter, int newMeter, int metrazh, int ring, int checked, int personId, String key,int userID, int weaveType,String extra) {
        //String query  = "INSERT INTO 'metrazh'('key','date','shift','salonNumber','machineNumber','allMeter','newMeter','ring','personID') values"+
        //       "('"+key+"', '"+date+"', '"+shiftNumber+"', '"+salonNumber+"', '"+machineNumber+"', '"+metrazh+"', '"+newMeter+"', "+
        //      ring+"', '"+personId+"')";
//        ContentValues values = new ContentValues();
//        values.put("key", key);
//        values.put("date",date);
//        values.put("shift",shiftNumber);
//        values.put("salonNumber",machineNumber);
//        values.put("newMeter",newMeter);
//        values.put("ring",ring);
//        values.put("personID",personId);

//        G.database.insert("metrazh",null,values);
        String query = "INSERT INTO 'metrazh'('key','date','shift','salonNumber','machineNumber','allMeter','oldMeter','newMeter','ring','eshteraki','weaveType','userID','personID','extra') values " +
                "('" + key + "', '" + date + "', '" + shiftNumber + "', '" + salonNumber + "', '" + machineNumber + "', '" + metrazh + "', '" + oldMeter + "', '" + newMeter + "', '" + ring + "', '" +
                checked + "', '" + weaveType + "', '" + userID + "', '" + personId +  "', '" + extra + "')";
        Log.i("Log", G.database.toString());
        try {
            G.database.execSQL(query);
        }catch (Exception e){
            Toast.makeText(testAcivity.this, "ماشین تکراری است.", Toast.LENGTH_LONG).show();
        }
        Log.i("Log", "dar jadval be dorosti vared shod.");
    }

    public void updateMachine(int date, int shiftNumber, int salonNumber, int machineNumber, int oldMeter, int newMeter, int metrazh, int ring, int checked, int personId, String key,int userID,String extra) {
        //String query  = "INSERT INTO 'metrazh'('key','date','shift','salonNumber','machineNumber','allMeter','newMeter','ring','personID') values"+
        //       "('"+key+"', '"+date+"', '"+shiftNumber+"', '"+salonNumber+"', '"+machineNumber+"', '"+metrazh+"', '"+newMeter+"', "+
        //      ring+"', '"+personId+"')";
//        ContentValues values = new ContentValues();
//        values.put("key", key);
//        values.put("date",date);
//        values.put("shift",shiftNumber);
//        values.put("salonNumber",machineNumber);
//        values.put("newMeter",newMeter);
//        values.put("ring",ring);
//        values.put("personID",personId);

//        G.database.insert("metrazh",null,values);
        if(extra.length()<1){
            extra = "ویرایش شده";
        }
        String query = "UPDATE metrazh SET allMeter = '"+metrazh+"', newMeter = '" + newMeter+"', ring = '" + ring + "',eshteraki = '"+checked +"', userID = '"+userID +"', extra = '"+ extra + "' WHERE " +
                "key = '"+ key + "'";
        Log.i("Log", G.database.toString());
        try {
            G.database.execSQL(query);
        }catch (Exception e){
            Log.i("Log", " اصلاح موفقیت آمیز نبود.");
        }
        Log.i("Log", " اطلاعات ماشین با موفقیت اصلاح شد");
        //Toast.makeText(testAcivity.this, "Successfully added to database", Toast.LENGTH_LONG).show();
    }
    public boolean generateWeaverName(EditText txtUserID,EditText txtUserName){
        boolean flag = false;
        int userID = 0;
        String userName = "";
        userID = Integer.parseInt(txtUserID.getText().toString());
        if(userID!=0) {
            try {
                Cursor curRead = G.database.rawQuery("SELECT * FROM weavers WHERE weaverID='" + userID + "'", null);
                curRead.moveToFirst();
                userName = curRead.getString(2);
                flag = true;
            } catch (Exception e) {
                Toast.makeText(testAcivity.this,"اطلاعات اشتباه است",Toast.LENGTH_SHORT).show();
                flag = false;
            }
        }else{
            Toast.makeText(testAcivity.this,"اطلاعات اشتباه است",Toast.LENGTH_SHORT).show();
        }
        txtUserName.setText(userName);

        return flag;
    }

    public boolean generateViewShift(ArrayList<workingClass> scrolledShift){
        boolean flag = false;
        try {
            Cursor curRead = G.database.rawQuery("SELECT * FROM metrazh WHERE date='" + machines.date + "' AND shift='" + machines.shiftNumber + "' AND salonNumber='" + machines.salonNumber + "'", null);

            while (curRead.moveToNext()) {
                String newMeter = curRead.getString(8);
                String oldMeter = curRead.getString(7);
                String ID1 = curRead.getString(2);
                String machineNumber = curRead.getString(5);
                String ring = curRead.getString(9);
                String personID = curRead.getString(12);
                String endMeter = curRead.getString(6);
                String weaveType = curRead.getString(11);
                String extra = curRead.getString(14);
                scrolledShift.add(new workingClass(newMeter, oldMeter, machineNumber, ring, personID,endMeter,weaveType,extra,ID1));
            }
            flag = true;
            curRead.close();
        }catch (Exception e){
            flag = false;
        }
        return flag;
    }

    public int getAutoID(String key){
        int autoId = 0;
        try{
            Cursor cursor = G.database.rawQuery("SELECT autoId FROM metrazh WHERE key='"+key+"'",null);
            while (cursor.moveToNext()){
                autoId = cursor.getInt(cursor.getColumnIndex("autoId"));
            }
            cursor.close();
        }
        catch (Exception e){
            autoId = -1;
        }
        return autoId;
    }


    //MACHINES ENTRY
    public void setMachines(EditText txtMachineNumber, EditText txtNewMeter, EditText txtRing, EditText txtZeroMeter, EditText txtUserID, EditText txtWeaveType, EditText txtExtra) {

        try {
            machines.userID = Integer.parseInt(txtUserID.getText().toString());
        } catch (Exception e) {
            machines.userID = 0;
        }
        try {
            machines.extra = txtExtra.getText().toString();
        } catch (Exception e) {
            machines.extra = "";
        }

        try {
            machines.machineNumber = Integer.parseInt(txtMachineNumber.getText().toString());
        } catch (Exception e) {
            machines.machineNumber = 0;
        }
        try {
            machines.newMeter = Integer.parseInt(txtNewMeter.getText().toString());
        } catch (Exception e) {
            machines.newMeter = 0;
        }
        try {
            machines.ring = Integer.parseInt(txtRing.getText().toString());
        } catch (Exception e) {
            machines.ring = 0;
        }
        try {
            machines.zeroMeter = Integer.parseInt(txtZeroMeter.getText().toString());
        } catch (Exception e) {
            machines.zeroMeter = -1;
        }
        try {
            machines.weaveType = Integer.parseInt(txtWeaveType.getText().toString());
        } catch (Exception e) {
            machines.zeroMeter = 0;
        }

    }

    //CALCULATE METER
    public int getOldMeter(int machine,int salon) {
        int oldMeter = 0;
        try{
            String query = "SELECT * FROM metrazh WHERE machineNumber ='"+machine +"' AND salonNumber = '"+salon + "'";
            Cursor cursor = G.database.rawQuery(query,null);

            while (cursor.moveToNext()){
                oldMeter = cursor.getInt(cursor.getColumnIndex("newMeter"));
            }
        }
        catch (Exception e){
            oldMeter = 0;
            Log.i("Log","get Exception" + e.getMessage().toString());
        }
        Log.i("Log","Old meter is: "+oldMeter);
        return oldMeter;
    }

    //KEY CALCULATOR
    public void calculateKey(EditText txtMachineNumber, int txtDate) {
        try {

            machines.key = txtMachineNumber.getText().toString() + Integer.toString(txtDate) + Integer.toString(machines.shiftNumber);

        } catch (Exception e) {
            machines.key = "0";
        }
        Log.i("Log", "Key successfully generated");
    }

    //CHECKING FOR HAVE VALUES IN FIELDS
    public boolean checkNotEmpty(EditText txtNewMeter, TextView txtDate, EditText txtMachineNumber) {
        if (txtNewMeter.getText().toString().length() > 0) {
            Log.i("Log", "meter is not empty");
            if (txtDate.getText().toString().length() > 0) {
                Log.i("Log", "Date is not empty");
                    if (txtMachineNumber.getText().toString().length() > 0) {
                        Log.i("Log", "Machine number is not empty");
                        return true;
                    }
            }
        }
        return false;
    }

    //FINAL METER CALCULATOR
    public int calculateMetrazh(int oldMeter, int newMeter, int zeroMeter) {
        int meter = 0;
        if (zeroMeter == -1) {
            meter = newMeter - oldMeter;
        } else {
            meter = zeroMeter + (newMeter-oldMeter);
            machines.newMeter = zeroMeter;
        }
        return meter;
    }

    public void calculateAutoID(){
        if(machines.isInserted==false){
            if(machines.isChanging==false){
                try{
                    Cursor cursor = G.database.rawQuery("SELECT autoId FROM metrazh",null);
                    machines.autoID = cursor.getCount()-1;
                    cursor.close();
             }
             catch (Exception e){
                 machines.autoID =0;
                 Log.i("Log","NOT INSERTED AND NOT CHANGED BUT GET EXCEPTION");
               }
          }
         else{
                 Log.i("Log","NOT INSERTED BUT CHANGED BUT GET EXCEPTION");
            }
        }
       else{
            machines.autoID = getAutoID(machines.key);
            Log.i("Log","INSERTED BUT NOT CHANGED");
       }
    }
    public boolean checkMachine(EditText txt1){
        int machineNumber  = Integer.parseInt(txt1.getText().toString());
        boolean flag = false;
        String query = "SELECT * FROM machines";
        Cursor cursor = G.database.rawQuery(query,null);
        while(cursor.moveToNext()){
            int machine = cursor.getInt(1);
            if(machineNumber == machine){
                flag = true;
            }
        }
        cursor.close();
        return flag;
    }
}
