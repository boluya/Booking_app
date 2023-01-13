package com.example.finalprojectprototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class CustomAdapter extends BaseAdapter {
    private HashMap<String,Integer> options;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(HashMap<String, Integer> options, Context context) {
        this.options = options;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return options.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = layoutInflater.inflate(R.layout.booking_options,viewGroup,false);
            TextView imgLabel = view.findViewById(R.id.label);
            ImageView img = view.findViewById(R.id.imagePlace);

            Object[] txt = options.keySet().toArray();
            Object[] pics = options.values().toArray();

            imgLabel.setText((CharSequence) txt[i]);
            img.setImageResource((Integer) pics[i]);
        }
        return view;
    }
}
