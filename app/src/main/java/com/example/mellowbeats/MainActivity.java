package com.example.mellowbeats;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mellowbeats.AdapterClass.ViewPagerAdapter;
import com.example.mellowbeats.CustomClass.Music;
import com.example.mellowbeats.UI.HomeFragment;
import com.example.mellowbeats.User.ProfileActivity;
import com.example.mellowbeats.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements androidx.appcompat.widget.SearchView.OnQueryTextListener {
    public static ActivityMainBinding binding;
    public static ArrayList<Music> MusicListSearch;
    public static boolean search;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);


        binding.pager.setAdapter(new ViewPagerAdapter(this));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tablayout, binding.pager, (tab, position) -> {
            switch (position) {
                case 0: {
                    tab.setText("Songs");
                    break;
                }
                case 1: {
                    tab.setText("Favorite");
                    break;
                }
                case 2: {
                    tab.setText("Playlist");
                    break;
                }
                default:
                    tab.setText("Playnext");
                    break;
            }
        });
        tabLayoutMediator.attach();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(MainActivity.this);
        return super.onCreateOptionsMenu(menu);

    }
//    onOptionsItemSelected


    //Override below methods:

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        MusicListSearch = new ArrayList<>();
        if (newText != null) {
            String userInput = newText.toLowerCase();
            for (Music music : HomeFragment.MusicListMV) {
                if (music.getTitle().toLowerCase().contains(userInput)) {
                    MusicListSearch.add(music);
                }
            }
            search = true;
            HomeFragment.songAdapter.updateMusicList(MusicListSearch);
        }
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.profile) {

            SharedPreferences preferences = getSharedPreferences("UserLogin", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            String userName = preferences.getString("UserName", "");
            editor.apply();
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);

    }


}




