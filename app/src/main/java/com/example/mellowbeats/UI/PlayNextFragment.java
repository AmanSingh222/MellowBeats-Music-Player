package com.example.mellowbeats.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mellowbeats.AdapterClass.FavouriteAdapter;
import com.example.mellowbeats.CustomClass.Music;
import com.example.mellowbeats.databinding.FragmentPlayNextBinding;

import java.util.ArrayList;


public class PlayNextFragment extends Fragment {


    public PlayNextFragment() {
        // Required empty public constructor
    }

     FragmentPlayNextBinding binding;

    public static   ArrayList<Music> playNextList = new ArrayList<>();
    private FavouriteAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentPlayNextBinding.inflate(inflater,container,false);
        binding.playNextRV.setHasFixedSize(true);
        binding.playNextRV.setItemViewCacheSize(13);
        binding.playNextRV.setLayoutManager(new LinearLayoutManager(getActivity()));
//        binding.playNextRV.setAdapter(new FavouriteAdapter(getActivity(),playNextList , true));
        adapter = new FavouriteAdapter(getActivity(),playNextList,true);
        binding.playNextRV.setAdapter(adapter);
        binding.AllSongs.setText("Play Next Songs : " + adapter.getItemCount());


        if (!playNextList.isEmpty()) {
            binding.instructionPN.setVisibility(View.GONE);
        }
        return  binding.getRoot();
    }
}