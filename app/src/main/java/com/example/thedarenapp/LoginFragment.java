package com.example.thedarenapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.thedarenapp.R;
import com.example.thedarenapp.databinding.ActivityMainBinding;
import com.example.thedarenapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private Button register, login;
//    public ActivityMainBinding  binding;

    public FragmentLoginBinding binding ;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentLoginBinding.inflate(inflater);
        // Inflate the layout for this fragment



        View view = inflater.inflate(R.layout.fragment_login, container, false);
        //associate button with navigation:
        binding.goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_login_to_registrationFragment);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);
    }
}