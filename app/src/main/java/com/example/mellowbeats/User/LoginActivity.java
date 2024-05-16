package com.example.mellowbeats.User;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mellowbeats.MainActivity;
import com.example.mellowbeats.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.Loginbtn.setOnClickListener(v -> {
            if(!binding.UserName.getText().toString().equals("") ){

                SharedPreferences preferences = getSharedPreferences("UserLogin",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("flag",true);
                editor.putString("UserName",binding.UserName.getText().toString());
                editor.apply();
                Intent NextScreen = new Intent(LoginActivity.this, MainActivity.class);
//                    NextScreen.putExtra("UserName",binding.UserName.getText().toString());
                startActivity(NextScreen);

            }

            else {
                Toast.makeText(LoginActivity.this, "  Plz Enter The Name ", Toast.LENGTH_SHORT).show();
            }

        });

    }
}