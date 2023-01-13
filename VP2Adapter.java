package com.example.finalprojectprototype;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VP2Adapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fragmentHolder;

    public VP2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentHolder.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentHolder.size();
    }

    public void setData(ArrayList<Fragment> fragments) {
        this.fragmentHolder = fragments;
    }
}
