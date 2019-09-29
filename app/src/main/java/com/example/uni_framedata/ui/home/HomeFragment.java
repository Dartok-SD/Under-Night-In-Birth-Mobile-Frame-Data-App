package com.example.uni_framedata.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.uni_framedata.ui.MyAdapter;
import com.example.uni_framedata.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView mRecylerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> characterNames;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mRecylerView = root.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecylerView.setLayoutManager(mLayoutManager);
        characterNames = new ArrayList<String>() {
            {
                add("Hyde");
                add("Linne");
                add("Waldstein");
                add("Carmine");
                add("Orie");
                add("Gordeau");
                add("Merkava");
                add("Vatista");
                add("Seth");
                add("Yuzuriha");
                add("Hilda");
                add("Eltnum");
                add("Chaos");
                add("Akatsuki");
                add("Nanase");
                add("Byakuya");
                add("Phonon");
                add("Mika");
                add("Wagner");
                add("Enkidu");
            }
        };
        mAdapter = new MyAdapter(characterNames,getActivity());
        mRecylerView.setAdapter(mAdapter);
        mRecylerView.setItemAnimator(new DefaultItemAnimator());

        return root;
    }
}