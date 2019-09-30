package com.example.uni_framedata.ui.textless;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uni_framedata.R;

public class TextlessFrameDataFragment extends Fragment {

    private TextlessFrameDataViewModel mViewModel;
    private String character = "";

    public static TextlessFrameDataFragment newInstance() {
        return new TextlessFrameDataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.textless_frame_data_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TextlessFrameDataViewModel.class);
        Bundle args = getArguments();
        if(args != null) {
            character = args.getString("character");
        }
        // TODO: Use the ViewModel
    }

}
