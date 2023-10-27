package com.example.thedarenapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;


import com.example.thedarenapp.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main); //this is old way, we want binding everywhere
         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}