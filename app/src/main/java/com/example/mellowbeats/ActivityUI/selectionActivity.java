package com.example.mellowbeats.ActivityUI;

import static com.example.mellowbeats.ActivityUI.PlaylistDetailsActivity.currentPlaylistPos;
import static com.example.mellowbeats.MainActivity.MusicListSearch;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mellowbeats.AdapterClass.AllSongAdapter;
import com.example.mellowbeats.CustomClass.Music;
import com.example.mellowbeats.MainActivity;
import com.example.mellowbeats.UI.HomeFragment;
import com.example.mellowbeats.databinding.ActivitySelectionBinding;
import java.util.ArrayList;

public class selectionActivity extends AppCompatActivity {

    public static ActivitySelectionBinding binding;
    AllSongAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySelectionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.selectionRV.setItemViewCacheSize(30);
        binding.selectionRV.setHasFixedSize(true);
        binding.selectionRV.setLayoutManager(new LinearLayoutManager(this));



        adapter = new AllSongAdapter(selectionActivity.this, HomeFragment.MusicListMV, false, true);
        binding.selectionRV.setAdapter(adapter);
        binding.searchViewSA.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MusicListSearch = new ArrayList<>();
                if (newText != null) {
                    String userInput = newText.toLowerCase();
                    for (Music song : HomeFragment.MusicListMV) {
                        if (song.getTitle().toLowerCase().contains(userInput)) {
                            MusicListSearch.add(song);
                        }
                    }
                    MainActivity.search = true;
                    adapter.updateMusicList(MusicListSearch);
                }
                return true;
            }
        });
        binding.check.setOnClickListener(v -> {
            Intent intent = new Intent(selectionActivity.this, PlaylistDetailsActivity.class);
            intent.putExtra("index", currentPlaylistPos);
            startActivity(intent);
            finish();

        });


}}