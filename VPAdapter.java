package com.example.finalprojectprototype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class VPAdapter extends PagerAdapter {
    private Context classContext;
    private int[] pics;
    private LayoutInflater inflater;

    public VPAdapter(Context c, int[] i){
        this.classContext = c;
        this.pics = i;
        this.inflater = (LayoutInflater) classContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return pics.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View picView = inflater.inflate(R.layout.vp_clicked_room,container,false);
        ImageView imageView = (ImageView) picView.findViewById(R.id.picImgView);
        imageView.setImageResource(pics[position]);
        Objects.requireNonNull(container).addView(picView);

        return picView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((LinearLayout) object);
    }


}
