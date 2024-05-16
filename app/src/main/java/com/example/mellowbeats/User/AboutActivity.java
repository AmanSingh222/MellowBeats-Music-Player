package com.example.mellowbeats.User;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mellowbeats.R;

import java.util.Objects;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Objects.requireNonNull(getSupportActionBar()).setTitle("About");

    }
}