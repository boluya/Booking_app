package com.example.finalprojectprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

public class clickedRoomActivity extends AppCompatActivity {

    ViewPager viewPager;
    int[] pics;
    VPAdapter vpAdapter;
    String str;
    TextView roomName;



    String[] states = {"Available", "Unavailable"};

    final String[] studySpots = {"0020","0050","0200",
                                "1010","1040A","1101","1121",
                                "2111","2130","2181","2240",};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_room);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        roomName = findViewById(R.id.nameOfPlace);

        // For the scrollable pictures
        String name = bundle.getString("name").replace('\n',' ');
        if(name.length() < 10) {
            str = name.substring(4,name.length());
        } else {
            str = name.substring(4,(name.length()-12));
        }
        pics = picsArraySetup(str);
        roomName.setText(name);

        viewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        vpAdapter = new VPAdapter(clickedRoomActivity.this, pics);
        viewPager.setAdapter(vpAdapter);

        // For the room/table booking
        ListView result = (ListView) findViewById(R.id.listView);
        HashMap<String,String> rows = new HashMap<>();
        for (int i =1; i<10; i++) {
            rows.put("Table "+i,states[(int) (Math.random()*2)]);
        }
        List<HashMap<String,String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this,listItems,
                                                R.layout.clicked_room_item,
                                                new String[]{"First Line","Second Line"},
                                                new int[]{R.id.txtView1,R.id.txtView2});
        Iterator it = rows.entrySet().iterator();
        while (it.hasNext()) {
            HashMap<String,String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }

        result.setAdapter(adapter);
        result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView checkKey = (TextView) view.findViewById(R.id.txtView1);
                TextView checkStatus = (TextView) view.findViewById(R.id.txtView2);
                String status = checkStatus.getText().toString();
                String key = checkKey.getText().toString();
                    if(status.equals("Available")){
                        alertDialogPopUp(key);
                    } else {
                        Toast.makeText(clickedRoomActivity.this,"This table is unavailable", Toast.LENGTH_SHORT).show();
                    }
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
                Intent intent = new Intent(clickedRoomActivity.this,HelpPage.class);
                startActivity(intent);
                return true;
            case R.id.homeScreen:
                alertDialogPopUp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void alertDialogPopUp(String p){
        AlertDialog.Builder builder = new AlertDialog.Builder(clickedRoomActivity.this);
        builder.setTitle("Confirm Booking");
        builder.setMessage("Do you want to book: " + p + "?" );
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(clickedRoomActivity.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(clickedRoomActivity.this,endScreen.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        builder.create();
        builder.show();
    }

    void alertDialogPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(clickedRoomActivity.this);
        builder.setTitle("Confirmation");
        builder.setMessage("Leave? Any progress made will be lost?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(clickedRoomActivity.this, BuildingChoices.class);
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

    int[] picsArraySetup(String c) {
        int[] h = {};
        switch (c) {
            case "0020":
                h = new int[]{R.drawable.eme_0020_reading_study_space_39pp,R.drawable.eme_0020_reading_study_space_12pp,R.drawable.eme_0020_reading_study_space_20pp_0};
                break;
            case "0050":
                h = new int[]{R.drawable.eme0050_01,R.drawable.eme0050_02};
                break;
            case "0200":
                h = new int[]{R.drawable.eme_0200_hallway_study_reading_space_8pp};
                break;
            case "1010":
                h = new int[]{R.drawable.eme_1010_reading_study_space_12pp};
                break;
            case "1040A":
                h = new int[]{R.drawable.eme_1040a_reading_study_space_12pp};
                break;
            case "1101":
                h = new int[]{R.drawable.eme1101_01,R.drawable.eme1101_02,R.drawable.eme1101_03};
                break;
            case "1121":
                h = new int[]{R.drawable.eme1121_01,R.drawable.eme1121_02,R.drawable.eme1121_03};
                break;
            case "2111":
                h = new int[]{R.drawable.eme2111_01,R.drawable.eme2111_02,R.drawable.eme2111_03};
                break;
            case "2130":
                h = new int[]{R.drawable.eme_2130_reading_study_space_18pp,R.drawable.eme_2130_reading_study_space_15pp};
                break;
            case "2181":
                h = new int[]{R.drawable.eme2181_01,R.drawable.eme2181_02};
                break;
            case "2240":
                h = new int[]{R.drawable.eme_2240_reading_study_space_13pp,R.drawable.eme_2240_reading_study_space_6pp,R.drawable.eme_2240_reading_study_space_2pp_0,R.drawable.eme_2240_reading_study_space_6pp_no_chairs};
                break;
        }
        return h;
    }


}