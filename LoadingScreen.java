package com.example.finalprojectprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LoadingScreen extends AppCompatActivity {
    private final String filename = "database.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File database = getFileStreamPath(filename);

        if(database != null) {
            try {
                PrintWriter pw = new PrintWriter(database);
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        setContentView(R.layout.activity_loading_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoadingScreen.this,BuildingChoices.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}