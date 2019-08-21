package com.example.pouya.accounter;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public class WorkingActivity extends AppCompatActivity {
    Machines machines = new Machines();
    final ArrayList<workingClass> workingArray = new ArrayList<workingClass>();
    String weavedID = "";
    String outputName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);
        ListView workingListView = (ListView) findViewById(R.id.workingListView);
        ImageButton btnAddReport = (ImageButton) findViewById(R.id.btnAddReport);
        Button finalPrint = (Button) findViewById(R.id.btnFinalPrint);
        final EditText ReportUserEditTxt = (EditText) findViewById(R.id.txtUserReport);
        final G createClass = new G();
        try {
            createClass.createOrOpenDataBase(WorkingActivity.this);
            //Toast.makeText(WorkingActivity.this, "برنامه آماده است.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //Toast.makeText(WorkingActivity.this, "ابتدا پوشه ها را ایجاد کنید", Toast.LENGTH_SHORT).show();
        }
        String weaverName = "";
        int weaverID;
        String weavedMeter;
        String newMeter;
        String oldMeter;
        String MachineNumber;
        String ring;
        String extra;
        String weaveType;
        String ID;


        String query = "SELECT * FROM metrazh WHERE date='" + machines.date + "' AND shift='" + machines.shiftNumber + "' AND salonNumber='" + machines.salonNumber + "' ORDER BY machineNumber ";

        Cursor curWeavers = G.database.rawQuery(query, null);
        while (curWeavers.moveToNext()) {
            try {
                Log.i("Log1: ", "WeaverName: " + curWeavers.getInt(2) + " weaverID: " + curWeavers.getString(1));
                weaverID = curWeavers.getInt(12);
                weavedMeter = "" + curWeavers.getInt(6);
                newMeter = "" + curWeavers.getInt(8);
                oldMeter = "" + curWeavers.getInt(7);
                MachineNumber = "" + curWeavers.getInt(5);
                ID = "" + curWeavers.getString(2);
                ring = "" + curWeavers.getInt(9);
                extra = "" + curWeavers.getString(14);
                weaveType = "" + curWeavers.getInt(14);
                String getWeaverName = "SELECT * FROM weavers WHERE weaverID = " + weaverID;
                Cursor curGetWeaver = G.database.rawQuery(getWeaverName, null);
                while (curGetWeaver.moveToNext()) {
                    weaverName = curGetWeaver.getString(2);
                }
                curGetWeaver.close();
                Log.i("Log2: ", "" + "Weaver Name: " + weaverName + " weavedMeter: " + weavedMeter + " newMeter: " + newMeter + " oldMeter: " + oldMeter);
                //Log.i("Log3: ", "" + "date: " + key + " Salon: " + salon + " report: " + report);
                workingClass workingclass = new workingClass(newMeter, oldMeter, MachineNumber, ring, weaverName, weavedMeter,weaveType,extra,ID);
                //Toast.makeText(reportActivity.this, "date: " + reportclass.getDate() + " Salon: " + reportclass.getShiftNumber(), Toast.LENGTH_SHORT).show();
                Log.i("Log4: ", "" + "weaver name: " + workingclass.getPersonID() + " weaverPersonleID: " + workingclass.getTotalMeter());
                workingArray.add(workingclass);
            } catch (Exception e) {

            }
        }
        curWeavers.close();
        final workingAdaptor adaptor = new workingAdaptor(this, workingArray);
        workingListView.setAdapter(adaptor);
        workingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workingClass currentWorker = adaptor.getItem(position);
                weavedID = currentWorker.getWeavedID();
                new AlertDialog.Builder(WorkingActivity.this).setTitle("حذف").setMessage("آیا از حذف این متراژ و مشخصات این ماشین مطمئن هستید؟")
                        .setPositiveButton("یله", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    String deleteQuery = "DELETE FROM metrazh WHERE key = " + weavedID;
                                    G.database.execSQL(deleteQuery);
                                    //Toast.makeText(WorkingActivity.this, "دستگاه با موفقیت حذف شد.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    Log.i("Log Delete: ", "ِ Delete Failed");
                                    //Toast.makeText(WorkingActivity.this, "حذف انجام نشد.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();

            }
        });

        btnAddReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ReportUserEditTxt.getText().toString().length() < 1) {
                    //Toast.makeText(WorkingActivity.this, "لطفا گزارش را وارد کنید.", Toast.LENGTH_SHORT).show();
                } else {
                    String query = "INSERT INTO 'privateReport'('messageKey','message') values ('" + (machines.date + "" + machines.shiftNumber) + "','" + ReportUserEditTxt.getText().toString() + "')";
                    G.database.execSQL(query);
                    //Toast.makeText(WorkingActivity.this, "با موفقیت اطلاعات وارد شد.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        finalPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final File exportDir = new File(Environment.getExternalStorageDirectory() + "/output");
                if (!exportDir.exists()) {
                    exportDir.mkdirs();
                }
                File file = new File(exportDir, "shift" + machines.date + machines.shiftNumber + "programm" + ".csv");
                try {
                    file.createNewFile();
                    CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
                    Cursor curCSV = G.database.rawQuery("SELECT * FROM metrazh WHERE date='" + machines.date + "' AND shift='" + machines.shiftNumber + "' AND salonNumber='" + machines.salonNumber + "'", null);
                    csvWriter.writeNext(curCSV.getColumnNames());
                    while (curCSV.moveToNext()) {
                        String arrStr[] = {curCSV.getString(0) + "," + curCSV.getString(1) + "," + curCSV.getString(2) + "," + curCSV.getString(3) + "," + curCSV.getString(4) + "," + curCSV.getString(5) + "," + curCSV.getString(6) + "," + curCSV.getString(7) + "," + curCSV.getString(8) + "," + curCSV.getString(9) + "," + curCSV.getString(10) + "," + curCSV.getString(11)+ "," + curCSV.getString(12) + "\n"};
                        csvWriter.writeNext(arrStr);
                    }
                    csvWriter.close();
                    curCSV.close();
                    //Toast.makeText(WorkingActivity.this, "اطلاعات شیفت با موفقیت در حافظه ذخیره شد", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    //Toast.makeText(WorkingActivity.this, "اطلاعات شیفت پیدا نشد!", Toast.LENGTH_SHORT).show();
                    Log.i("Log", e.getMessage().toString());

                }
                String fileName = "shift" + machines.date + machines.shiftNumber + "PRINT" + ".csv";
                File printfile = new File(exportDir, fileName);
                String arrColumn[] = {"ملاحظات", "سایر تاخیرات", "نوع نخ", "رینگ دستگاه", "نام و نام خانوادگی بافنده", "متراژ تولید", "متراژ بعدی", "متراژ قبلی", "شماره ماشین"};
                try {
                    printfile.createNewFile();
                    CSVWriter csvWriter = new CSVWriter(new FileWriter(printfile));
                    Cursor curCSV = G.database.rawQuery("SELECT * FROM metrazh WHERE date='" + machines.date + "' AND shift='" + machines.shiftNumber + "' AND salonNumber='" + machines.salonNumber + "'", null);
                    csvWriter.writeNext(arrColumn);
                    while (curCSV.moveToNext()) {
                        String arrStr[] = {curCSV.getString(14), "", curCSV.getString(11), curCSV.getString(9), curCSV.getString(12), curCSV.getString(6), curCSV.getString(8), curCSV.getString(7), curCSV.getString(5)};
                        csvWriter.writeNext(arrStr);
                    }
                    csvWriter.close();
                    curCSV.close();
                    //Toast.makeText(WorkingActivity.this, "اطلاعات شیفت با موفقیت در حافظه ذخیره شد", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    //Toast.makeText(WorkingActivity.this, "اطلاعات شیفت پیدا نشد!", Toast.LENGTH_SHORT).show();
                    Log.i("Log", e.getMessage().toString());

                }
                try {
                    final XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("polyramin");
                    Row row = sheet.createRow(0);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(machines.personId);
                    cell = row.createCell(1);
                    cell.setCellValue("سرشیفت: ");
                    //cell = row.createCell(3);
                    String shift = "";
                    switch (machines.shiftNumber) {
                        case 1:
                            shift = "صبح";
                            break;
                        case 2:
                            shift = "ظهر";
                            break;
                        case 3:
                            shift = "شب";
                            break;
                        case 4:
                            shift = "D";
                            break;
                        default:
                            shift = "نامعلوم";
                            break;
                    }
                    int printDate = machines.date;
                    int day = printDate % 100;
                    printDate = printDate / 100;
                    int month = printDate % 100;
                    printDate = printDate / 100;


                    cell = row.createCell(5);
                    cell.setCellValue(printDate);
                    cell = row.createCell(6);
                    cell.setCellValue(month);
                    cell = row.createCell(7);
                    cell.setCellValue(day);
                    cell = row.createCell(8);
                    cell.setCellValue("تاریخ: ");
                    row = sheet.createRow(1);
                    cell = row.createCell(7);
                    cell.setCellValue(shift);
                    cell = row.createCell(8);
                    cell.setCellValue("شیفت");
                    int rowCount = 2;
                    row = sheet.createRow(rowCount);

                    //edit Styles
                    //Header Font Def:
                    Font headerFont = sheet.getWorkbook().createFont();
                    //headerFont.setFontName("Arial");
                    headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);

                    //Body Font Def
                    Font bodyFont = sheet.getWorkbook().createFont();
                    //bodyFont.setFontName("Arial");


                    //header Style
                    CellStyle header = sheet.getWorkbook().createCellStyle();
                    header.setFont(headerFont);
                    header.setBorderBottom(CellStyle.BORDER_MEDIUM);
                    header.setBorderLeft(CellStyle.BORDER_MEDIUM);
                    header.setBorderRight(CellStyle.BORDER_MEDIUM);
                    header.setBorderTop(CellStyle.BORDER_MEDIUM);


                    //body Style
                    CellStyle body = sheet.getWorkbook().createCellStyle();
                    body.setFont(bodyFont);
                    body.setBorderBottom(CellStyle.BORDER_THIN);
                    body.setBorderLeft(CellStyle.BORDER_THIN);
                    body.setBorderRight(CellStyle.BORDER_THIN);
                    body.setBorderTop(CellStyle.BORDER_THIN);

                    //write headers to excell
                    for (int i = 0; i < arrColumn.length; i++) {
                        cell = row.createCell(i);
                        cell.setCellStyle(header);
                        cell.setCellValue(arrColumn[i]);
                    }
                    //finish headers

                    //go to body
                    rowCount++;
                    String name = "";
                    int personelId = 0;
                    try {
                        Cursor curCSV = G.database.rawQuery("SELECT * FROM metrazh WHERE date='" + machines.date + "' AND shift='" + machines.shiftNumber + "' AND salonNumber='" + machines.salonNumber + "'", null);
                        curCSV.moveToFirst();
                        while (curCSV.moveToNext()) {
                            row = sheet.createRow(rowCount);
                            personelId = curCSV.getInt(12);
                            Cursor cursorName = G.database.rawQuery("SELECT * FROM weavers WHERE weaverID='" + personelId + "'", null);
                            cursorName.moveToFirst();
                            Log.i("Log","paygah dade dovom" + cursorName.getString(2));
                            Log.i("Log","paygah dade khande shod");
                            String arrStr[] = {curCSV.getString(14), "", curCSV.getString(11), curCSV.getString(9), name, curCSV.getString(6), curCSV.getString(8), curCSV.getString(7), curCSV.getString(5)};
                            for (int i = 0; i < 2; i++) {
                                cell = row.createCell(i);
                                cell.setCellStyle(body);
                                cell.setCellValue("");

                            }
                            for (int i = 2; i < arrStr.length; i++) {
                                cell = row.createCell(i);
                                cell.setCellStyle(body);
                                cell.setCellValue(arrStr[i]);
                                Log.i("Log","dade neveshte shode dar jadval : " + arrStr[i]);
                            }
                            cursorName.close();
                            rowCount++;
                        }
                        curCSV.close();
                    } catch (Exception e) {
                        //Toast.makeText(WorkingActivity.this, "اطلاعات شیفت پیدا نشد!", Toast.LENGTH_SHORT).show();
                        Log.i("Log", e.getMessage().toString());

                    }
                    rowCount += 2;
                    row = sheet.createRow(rowCount);
                    cell = row.createCell(7);
                    cell.setCellValue("امضا");
                    sheet.setColumnWidth(0, 5000);
                    sheet.setColumnWidth(1, 2000);
                    sheet.setColumnWidth(2, 1500);
                    sheet.setColumnWidth(3, 2500);
                    sheet.setColumnWidth(4, 3000);
                    sheet.setColumnWidth(5, 2000);
                    sheet.setColumnWidth(6, 2000);
                    sheet.setColumnWidth(7, 2000);
                    sheet.setColumnWidth(8, 1500);


                    //write file to device memory
                    int random = new Random().nextInt(101);
                    String randomString = String.valueOf(random);
                    outputName = "try" + machines.date + machines.shiftNumber + randomString + ".xlsx";
                    try {
                        new AlertDialog.Builder(WorkingActivity.this).setTitle("اخطار").setMessage("آیا از گرفتن خروجی مطمئن هستید ؟ بعد از گرفتن خروجی امکان تغییر در داده ها وجود ندارد.")
                                .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FileOutputStream outputStream = null;
                                        try {
                                            outputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/output/" + outputName);
                                        } catch (FileNotFoundException e) {
                                            e.printStackTrace();
                                            Log.i("Log",e.toString());
                                        }
                                        try {
                                            workbook.write(outputStream);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                            Log.i("Log",e.toString());
                                        }
                                        openFile(exportDir,outputName);
                                    }
                                }).setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create().show();

                        //FileOutputStream outputStream = new FileOutputStream(Environment.getExternalStorageDirectory() + "/output/" + outputName);
                        //workbook.write(outputStream);
                    } catch (Exception e) {
                        Log.i("Log", e.toString());

                    }
                }catch (Exception e){
                    Log.i("Error",e.toString());
                }


                // finalizing file for opening

                //openFile(exportDir,outputName);


            }
        });

    }

    private void openFile(File Address,String inputFileName) {
        try {
            File file = new File(Address , inputFileName);
            String fileExtension = inputFileName.substring(inputFileName.lastIndexOf("."));
            Uri path = Uri.fromFile(file);
            Intent fileIntent = new Intent(Intent.ACTION_VIEW);
            fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if(Build.VERSION.SDK_INT>=24){
                try{
                    Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                    m.invoke(null);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            fileIntent.setDataAndType(path, "*/*");
            startActivity(fileIntent);

        } catch (ActivityNotFoundException e) {
            //Toast.makeText(WorkingActivity.this, "Cant Find Your File", Toast.LENGTH_LONG).show();
            Log.i("Log", e.toString());
        }
    }

}
