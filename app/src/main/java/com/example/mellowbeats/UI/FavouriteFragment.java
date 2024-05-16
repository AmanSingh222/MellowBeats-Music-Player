package com.example.mellowbeats.UI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mellowbeats.AdapterClass.FavouriteAdapter;
import com.example.mellowbeats.CustomClass.Music;
import com.example.mellowbeats.databinding.FragmentFavouriteBinding;
import java.util.ArrayList;


public class FavouriteFragment extends Fragment {

    FragmentFavouriteBinding binding;
    private FavouriteAdapter adapter;
    public static ArrayList<Music> favouriteSongs = new ArrayList<>();
    public static boolean favouritesChanged = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false);

        favouriteSongs = Music.checkPlaylist(favouriteSongs);

        binding.FavRecycler.setHasFixedSize(true);
        binding.FavRecycler.setItemViewCacheSize(13);
        binding.FavRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FavouriteAdapter(getActivity(), favouriteSongs, false);
        binding.FavRecycler.setAdapter(adapter);

        updateAdapter(); // Call updateAdapter to notify the adapter about data changes

        // Set the text after calling updateAdapter
        binding.AllSongs.setText("All Favourite Songs : " + adapter.getItemCount());

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateAdapter() {
        adapter.notifyDataSetChanged();
        // After notifying the adapter, update the text of AllSongs
        binding.AllSongs.setText("All Favourite Songs : " + adapter.getItemCount());
    }}

