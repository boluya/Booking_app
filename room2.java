package com.example.finalprojectprototype;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.HashMap;


public class room2 extends Fragment {

    private GridView gridView;
    private HashMap<String,Integer> content = new HashMap<String,Integer>();

    public room2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        content.put("EME 2130"+"\n"+"Study Space",R.drawable.eme_2130_reading_study_space_18pp);
        content.put("EME 2181",R.drawable.eme2181_01);
        content.put("EME 2111",R.drawable.eme2111_01);
        content.put("EME 2240"+"\n"+"Study Space",R.drawable.eme_2240_reading_study_space_13pp);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_room2, container, false);
        gridView  = (GridView) v.findViewById(R.id.gridViewFloor2);
        CustomAdapter myAdapter = new CustomAdapter(content,getActivity());
        gridView.setAdapter(myAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView buildingName = (TextView) view.findViewById(R.id.label);
                String check = buildingName.getText().toString();
                if(!check.contains("Study Space")){
                    Toast.makeText(getActivity(), "Please select a Study Space", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), clickedRoomActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", content.keySet().toArray()[i].toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

}