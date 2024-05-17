package com.example.mellowbeats.User;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mellowbeats.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");

        SharedPreferences preferences = getSharedPreferences("UserLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        preferences.getString("UserName", binding.ProfileName.toString());
        String userName = preferences.getString("UserName", "");
        binding.ProfileName.setText(userName);
        editor.apply();


        binding.FeedbackAccount.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, FeedbackActivity.class);
            startActivity(intent);
        });
        binding.AboutAccount.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, AboutActivity.class);
            startActivity(intent);
        });

        binding.Logout.setOnClickListener(v -> {
//                SharedPreferences sharedPreferences = getSharedPreferences("UserLogin",MODE_PRIVATE);
//                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor1 = sharedPreferences.edit();
//                editor.putBoolean("flag",false);
//                editor.apply();

            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
            builder.setTitle("Remove")
                    .setMessage("Do you want to Logout")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences sharedPreferences = getSharedPreferences("UserLogin", MODE_PRIVATE);
                            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor1 = sharedPreferences.edit();
                            editor.putBoolean("flag", false);
                            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                            startActivity(intent);
                            editor.apply();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog customDialog = builder.create();
            customDialog.show();
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GREEN);


        });
        binding.Settings.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, SettingsActivity.class);
            startActivity(intent);
        });



        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}