package com.example.mellowbeats.User;


import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mellowbeats.MainActivity;
import com.example.mellowbeats.R;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setStatusBarColor();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("UserLogin",MODE_PRIVATE);
                Boolean Chack = preferences.getBoolean("flag",false);
                Intent intent;
                if(Chack  ){
                    intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                else {
                    intent=  new Intent(SplashScreenActivity.this,LoginActivity.class);
                }
                startActivity(intent);
                finish();

            }
        },2000);
    }


    private void setStatusBarColor() {
        Window window = getWindow();
        int statusBarColor = ContextCompat.getColor(getApplicationContext(), R.color.black);
        window.setStatusBarColor(statusBarColor);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

}
