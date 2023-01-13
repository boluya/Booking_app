package com.example.finalprojectprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BuildingChoices extends AppCompatActivity implements CustomLoginDialog.DialogListener {
    private String user;
    private String userNum;
    private ArrayList<String[]> dataContainer;
    private final String filename = "database.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_choices);

        File database = getFileStreamPath(filename);
        if(!dbStatus(database)) {
            database = createDB(filename);
        }

        Button eme = findViewById(R.id.eme);
        Button com = findViewById(R.id.com);
        Button asc = findViewById(R.id.asc);
        Button art = findViewById(R.id.ART);
        Button sci = findViewById(R.id.sci);
        Button fip = findViewById(R.id.fip);
        Button lib = findViewById(R.id.lib);
        Button adm = findViewById(R.id.adm);
        Button viewBookings = findViewById(R.id.viewBookings);
        Button logIn = findViewById(R.id.logIn);

        dataContainer = dbReader(filename);
        if(!dataContainer.isEmpty()) {
            user = dataContainer.get(0)[0];
            userNum = dataContainer.get(0)[1];
        }

        activityCall(eme);
        activityCall(com);
        activityCall(asc);
        activityCall(art);
        activityCall(sci);
        activityCall(fip);
        activityCall(lib);
        activityCall(adm);
        activityCall(viewBookings);
        activityCall(logIn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.help:
                Intent intent = new Intent(BuildingChoices.this,HelpPage.class);
                startActivity(intent);
                return true;
            case R.id.homeScreen:
                Toast.makeText(this, "Already on Home Screen", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    void activityCall(Button b) {
        CustomLoginDialog cld = new CustomLoginDialog();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast t = Toast.makeText(BuildingChoices.this,"Please Select EME",Toast.LENGTH_SHORT);
                Toast loginMessage = Toast.makeText(BuildingChoices.this, "Sign-In required", Toast.LENGTH_SHORT);
                switch(b.getId()) {
                    case R.id.logIn:
                        if(user != null && userNum != null) {
                            Toast.makeText(BuildingChoices.this, "You're already signed in", Toast.LENGTH_SHORT).show();
                        } else {
                            cld.show(getSupportFragmentManager(), "Login Dialog");
                            dbWriter(filename, user, userNum);
                        }
                        break;
                    case R.id.viewBookings:
                        if(userNum == null) {
                            loginMessage.show();
                        }else {
                            Toast.makeText(BuildingChoices.this, "Still under construction", Toast.LENGTH_SHORT).show();
//                            Intent bookingsPage = new Intent(BuildingChoices.this, ViewBookings.class);
//                            Bundle bundle = new Bundle();
//                            bundle.putString("name",user);
//                            bundle.putString("name",userNum);
//                            bookingsPage.putExtras(bundle);
//                            startActivity(bookingsPage);
                        }
                        break;
                    case R.id.com:
                    case R.id.adm:
                    case R.id.asc:
                    case R.id.ART:
                    case R.id.sci:
                    case R.id.fip:
                    case R.id.lib:
                        if(userNum == null) {
                            loginMessage.show();
                        }else {
                            t.show();
                        }
                        break;
                    default:
                        if(userNum == null) {
                            loginMessage.show();
                        }else {
                            Intent intent = new Intent(BuildingChoices.this, MainActivity.class);
                            startActivity(intent);
                        }
                                        }
            }
        });
    }

    @Override
    public void pullInputs(String u, String n) {
        this.user = u;
        this.userNum = n;

    }

    private ArrayList dbReader(String filename) {
        ArrayList<String[]> container = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while((line = br.readLine()) != null) {
                String[] content = line.split("\\t");
                container.add(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return container;
    }

    private void dbWriter(String file,String u, String un ) {
        String fileContents = u + "\t" + un + "\n";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(file,MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private File createDB(String filename) {
        File f = new File(filename);
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    private boolean dbStatus(File file) {
        if(file != null) {
            return true;
        }
        else {
            return false;
        }
    }
}