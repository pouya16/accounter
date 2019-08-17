package com.example.pouya.accounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class shiftdateActivity extends AppCompatActivity {
    Machines machines = new Machines();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shiftdate);
        Spinner shiftSpinner = (Spinner) findViewById(R.id.spinnerShifts);
        Spinner daySpinner = (Spinner) findViewById(R.id.spinnerDay);
        Spinner monthSpinner = (Spinner) findViewById(R.id.spinnerMonth);
        Spinner yearSpinner = (Spinner) findViewById(R.id.spinnerYear);
        Spinner salonSpinner = (Spinner) findViewById(R.id.spinnerSalonNumber);
        final TextView txtDate = (TextView) findViewById(R.id.txtDate);
        final EditText txtSalon = (EditText) findViewById(R.id.txtSalonNumber);
        final TextView txtPersonId = (TextView) findViewById(R.id.txtPersonID);
        Button btnNext = (Button) findViewById(R.id.btnNextPage);

        ArrayAdapter<String> shiftAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Constants.workingShifts);
        ArrayAdapter<String> dayAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Constants.Days);
        ArrayAdapter<String> monthAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Constants.Months);
        ArrayAdapter<String> yearAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Constants.Years);
        ArrayAdapter<String> salonAdaptor = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, Constants.salonNumbers);
        shiftAdaptor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        dayAdaptor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        monthAdaptor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        yearAdaptor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        salonAdaptor.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        shiftSpinner.setAdapter(shiftAdaptor);
        daySpinner.setAdapter(dayAdaptor);
        monthSpinner.setAdapter(monthAdaptor);
        yearSpinner.setAdapter(yearAdaptor);
        salonSpinner.setAdapter(salonAdaptor);
        final G createDirectory = new G();


        // spinners
        //spinners
        salonSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        txtSalon.setText("1");
                        break;
                    case 1:
                        txtSalon.setText("2");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtSalon.setText("1");
            }
        });
        shiftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        machines.shiftNumber = 1;
                        break;
                    case 1:
                        machines.shiftNumber = 2;
                        break;
                    case 2:
                        machines.shiftNumber = 3;
                        break;
                    case 3:
                        machines.shiftNumber = 4;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                machines.shiftNumber = 1;


            }
        });
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        machines.day = "01";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 1:
                        machines.day = "02";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 2:
                        machines.day = "03";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 3:
                        machines.day = "04";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 4:
                        machines.day = "05";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 5:
                        machines.day = "06";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 6:
                        machines.day = "07";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 7:
                        machines.day = "08";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 8:
                        machines.day = "09";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 9:
                        machines.day = "10";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 10:
                        machines.day = "11";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 11:
                        machines.day = "12";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 12:
                        machines.day = "13";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 13:
                        machines.day = "14";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 14:
                        machines.day = "15";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 15:
                        machines.day = "16";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 16:
                        machines.day = "17";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 17:
                        machines.day = "18";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 18:
                        machines.day = "19";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 19:
                        machines.day = "20";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 20:
                        machines.day = "21";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 21:
                        machines.day = "22";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 22:
                        machines.day = "23";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 23:
                        machines.day = "24";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 24:
                        machines.day = "25";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 25:
                        machines.day = "26";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 26:
                        machines.day = "27";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 27:
                        machines.day = "28";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 28:
                        machines.day = "29";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 29:
                        machines.day = "30";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 30:
                        machines.day = "31";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                machines.day = "1";
                machines.dateString = machines.year + machines.month + machines.day;
                txtDate.setText(machines.dateString);

            }
        });
        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        machines.month = "01";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 1:
                        machines.month = "02";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 2:
                        machines.month = "03";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 3:
                        machines.month = "04";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 4:
                        machines.month = "05";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 5:
                        machines.month = "06";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 6:
                        machines.month = "07";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 7:
                        machines.month = "08";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 8:
                        machines.month = "09";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 9:
                        machines.month = "10";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 10:
                        machines.month = "11";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 11:
                        machines.month = "12";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                machines.month = "1";
                machines.dateString = machines.year + machines.month + machines.day;
                txtDate.setText(machines.dateString);
            }
        });
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        machines.year = "1397";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 1:
                        machines.year = "1398";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 2:
                        machines.year = "1399";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 3:
                        machines.year = "1400";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 4:
                        machines.year = "1401";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                    case 5:
                        machines.year = "1402";
                        machines.dateString = machines.year + machines.month + machines.day;
                        txtDate.setText(machines.dateString);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                machines.year = "1397";
                machines.dateString = machines.year + machines.month + machines.day;
                txtDate.setText(machines.dateString);
            }
        });


        //defaults
        txtPersonId.setText(Constants.enteredUser);
        txtPersonId.setKeyListener(null);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    machines.date = Integer.parseInt(machines.dateString);
                }catch (Exception e){
                    Toast.makeText(shiftdateActivity.this,e.toString(),Toast.LENGTH_LONG).show();
                }
                machines.salonNumber = Integer.parseInt(txtSalon.getText().toString());
                Intent intent = new Intent(shiftdateActivity.this,testAcivity.class);
                intent.putExtra("machinesDate",machines.date);
                intent.putExtra("machinesShift",machines.shiftNumber);
                intent.putExtra("machinesSalon",machines.salonNumber);
                createDirectory.createAppDirectories(shiftdateActivity.this);
                shiftdateActivity.this.startActivity(intent);
            }
        });




    }
    public void setMachines(EditText txtDate, EditText txtSalon, EditText txtPersonId) {
        try {
            machines.date = Integer.parseInt(txtDate.getText().toString());
        } catch (Exception e) {
            machines.date = 0;
        }
        try {
            machines.salonNumber = Integer.parseInt(txtSalon.getText().toString());
        } catch (Exception e) {
            machines.salonNumber = 0;
        }
        try {
            machines.personId = Integer.parseInt(txtPersonId.getText().toString());
        } catch (Exception e) {
            machines.personId = 0;
        }

    }
}
