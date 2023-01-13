package com.example.finalprojectprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    Button f0,f1,f2;
    ImageView building;
    FragmentManager fragManager = getSupportFragmentManager();
    FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();

        building = findViewById(R.id.imageView2);
        f0 = (Button) findViewById(R.id.button);
        f1 = (Button) findViewById(R.id.button4);
        f2 = (Button) findViewById(R.id.button5);

        f0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                building.setImageResource(R.drawable.eme_floor_0_graphic);
                transaction = fragManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container_view,room.class,null);
                transaction.commit();
            }
        });

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                building.setImageResource(R.drawable.eme_floor_1_graphic);
                transaction = fragManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container_view,room1.class,null);
                transaction.commit();

            }
        });

        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                building.setImageResource(R.drawable.eme_floor_2_graphic);
                transaction = fragManager.beginTransaction();
                transaction.setReorderingAllowed(true);
                transaction.replace(R.id.fragment_container_view,room2.class,null);
                transaction.commit();

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.help_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.help:
                Intent intent = new Intent(MainActivity.this,HelpPage.class);
                startActivity(intent);
                return true;
            case R.id.homeScreen:
                alertDialogPopUp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    void alertDialogPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Leave? Any progress made will be lost?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, BuildingChoices.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create();
        builder.show();
    }

}