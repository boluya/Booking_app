package com.example.finalprojectprototype;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class ViewBookings extends AppCompatActivity implements TabLayoutMediator.TabConfigurationStrategy {
    ViewPager2 vp2;
    TabLayout tabLay;
    ArrayList<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);
        Intent intent = getIntent();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vp2 = findViewById(R.id.viewPager2);
        tabLay = findViewById(R.id.tabLayout);
        titles = new ArrayList<String>();
        titles.add("Booked Rooms");
        titles.add("Booked Tables");
        setViewPagerAdapter();
        new TabLayoutMediator(tabLay,vp2,ViewBookings.this).attach();

    }

    public void setViewPagerAdapter() {
        VP2Adapter vp2Adapter = new VP2Adapter(this);
        ArrayList<Fragment> fragList = new ArrayList<>();
        fragList.add(new BookedRoomFrag());
        fragList.add(new BookedFloorFrag());
        vp2Adapter.setData(fragList);
        vp2.setAdapter(vp2Adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bookings_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.help:
                Intent intent = new Intent(ViewBookings.this,HelpPage.class);
                startActivity(intent);
                return true;
            case R.id.homeScreen:
                Intent intent1 = new Intent(ViewBookings.this,BuildingChoices.class);
                startActivity(intent1);
                return true;
            case R.id.delSingle:
                return true;
            case R.id.delAll:
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
        tab.setText(titles.get(position));
    }
}