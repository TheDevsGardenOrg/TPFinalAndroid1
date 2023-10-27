package com.example.thedarenapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.thedarenapp.databinding.ActivityMainBinding;

public class LoginFragment extends Fragment {

    private ActivityMainBinding binding;
public LoginFragment(){

}
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        //go straight to navigation

}}

