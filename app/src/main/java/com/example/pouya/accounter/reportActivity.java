package com.example.pouya.accounter;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class reportActivity extends AppCompatActivity {
    Machines machines = new Machines();
    final ArrayList<reportClass> reportArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        final ListView reportListView = (ListView) findViewById(R.id.reportListView);
        ImageButton deleteAll = (ImageButton) findViewById(R.id.deleteImageBtn);
        final G createClass = new G();
        createClass.createOrOpenDataBase(reportActivity.this);
        try{
            createClass.createOrOpenDataBase(reportActivity.this);
            Toast.makeText(reportActivity.this,"برنامه آماده است.",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Toast.makeText(reportActivity.this,"ابتدا پوشه ها را ایجاد کنید",Toast.LENGTH_SHORT).show();
        }


        refreshList();
        final reportAdaptor adaptor = new reportAdaptor(this, reportArray);
        reportListView.setAdapter(adaptor);

        reportListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final reportClass selectedReport = adaptor.getItem(position);
                new AlertDialog.Builder(reportActivity.this).setTitle("حذف گزارش").setMessage("آیا از حذف این گزارش مطمئنید؟")
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String deleteDate = selectedReport.getDate();
                                String deleteSalon = selectedReport.getShiftNumber();
                                Log.i("Log","begining of deletion. delete date is: " + deleteDate + " salon: " + deleteSalon);
                                try {
                                    String deleteKey = deleteDate + deleteSalon;
                                    String deleteReportQuery = "DELETE FROM privateReport WHERE messageKey=" + Integer.parseInt(deleteKey);
                                    G.database.execSQL(deleteReportQuery);
                                    Toast.makeText(reportActivity.this, "گزارش به درستی پاک شد", Toast.LENGTH_SHORT).show();
                                    adaptor.remove(selectedReport);
                                    adaptor.notifyDataSetChanged();
                                    Log.i("Log","حذف با موفقیت انجام شد.");
                                    reportListView.setAdapter(adaptor);

                                }catch (Exception e){
                                    Toast.makeText(reportActivity.this, "عدم موفقیت در حذف گزارش", Toast.LENGTH_SHORT).show();
                                    Log.i("Log","not deleted: "+e.toString());
                                }
                            }
                        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }
        });

        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(reportActivity.this).setTitle("حذف کامل گزارش").setMessage("آیا مطمئنید میخواهید تمام گزارشات را حذف نمایید؟")
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    String deleteReportQuery = "DELETE FROM privateReport";
                                    G.database.execSQL(deleteReportQuery);
                                    Toast.makeText(reportActivity.this, "گزارش به درستی پاک شد", Toast.LENGTH_SHORT).show();
                                    adaptor.notifyDataSetChanged();
                                    reportListView.setAdapter(adaptor);

                                }catch (Exception e){
                                    Toast.makeText(reportActivity.this, "عدم موفقیت در حذف گزارش", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
            }

        });

    }
    public void refreshList(){
        if(reportArray.size()>0){
            reportArray.clear();
        }
        String report="";
        String reportDate = "";
        String key = "";
        String salonNumber = "";
        int salon = 0;
        Log.i("Log0: ",""+"date: "+key+" Salon: "+salon+" txtMessage: "+report);
        try {

            Cursor curReport = G.database.rawQuery("SELECT * FROM privateReport", null);
            while (curReport.moveToNext()) {
                Log.i("Log1: ", "key: " + curReport.getInt(1) + " report: " + curReport.getString(2));
                reportDate = "" + curReport.getInt(1);
                report = curReport.getString(2);
                salon = Integer.parseInt(reportDate) % 10;
                salonNumber = ""+salon;
                key = "" + Integer.parseInt(reportDate) / 10;
                Log.i("Log2: ", "" + "date: " + key + " Salon: " + salon + " txtMessage: " + report);
                try {
                    Log.i("Log3: ", "" + "date: " + key + " Salon: " + salon + " report: " + report);
                    reportClass reportclass = new reportClass(key, salonNumber, report);
                    Toast.makeText(reportActivity.this, "date: " + reportclass.getDate() + " Salon: " + reportclass.getShiftNumber(), Toast.LENGTH_SHORT).show();
                    Log.i("Log4: ", "" + "date: " + reportclass.getDate() + " Salon: " + reportclass.getShiftNumber());
                    reportArray.add(reportclass);
                } catch (Exception e) {
                    Log.i("Log: ", "Fatal error" + e.getMessage());

                }
            }
        }catch (Exception e){

            Log.i("Log: ", "Fatal error" + e.getMessage());
        }

    }
}
