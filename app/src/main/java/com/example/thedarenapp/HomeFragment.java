package com.example.thedarenapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {
    public HomeFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
     View view = inflater.inflate(R.layout.fragment_home, container, false);
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment);
        return view;
    }
}