package com.example.pouya.accounter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class adminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        final Constants constants = new Constants();
        Button btnFile = (Button) findViewById(R.id.btnFile);
        Button btnDB = (Button) findViewById(R.id.btnDB);
        Button btnSetUsers = (Button) findViewById(R.id.btnSetUsers);
        Button btnReport = (Button) findViewById(R.id.btnReportActivity);
        Button btnSetMachines = (Button) findViewById(R.id.btnSetMachines);
        Button btnWeavers = (Button) findViewById(R.id.btnWeavers);


        btnSetUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, userNamesActivity.class);
                adminActivity.this.startActivity(intent);
            }
        });

        btnSetMachines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, machinesActivity.class);
                adminActivity.this.startActivity(intent);
            }
        });

        btnWeavers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, weaverActivity.class);
                adminActivity.this.startActivity(intent);
            }
        });


        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String directory = G.directory;
                File dbMaker = new File(directory);
                if(!dbMaker.exists()){
                    dbMaker.mkdirs();
                    Toast.makeText(adminActivity.this,"پوشه ها ساخته شد",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(adminActivity.this,"پوشه ها قبلا ساخته شده بود",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminActivity.this, reportActivity.class);
                adminActivity.this.startActivity(intent);
            }
        });
    }

}
