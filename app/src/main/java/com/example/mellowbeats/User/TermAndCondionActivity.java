package com.example.mellowbeats.User;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mellowbeats.databinding.ActivityTermAndCondionBinding;

import java.util.Objects;

public class TermAndCondionActivity extends AppCompatActivity {
    ActivityTermAndCondionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTermAndCondionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setTitle("Privacy Policy");

    }
}

