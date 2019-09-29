package com.example.uni_framedata.ui.photo;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uni_framedata.CharacterFrameDataActivity;
import com.example.uni_framedata.R;

public class PhotoFrameDataFragment extends Fragment {

    private PhotoFrameDataViewModel mViewModel;
    private CharacterFrameDataActivity myActivity;
    private TextView myTextView;
//    private Boolean foundChara = false;
    private String character = "";
    public static PhotoFrameDataFragment newInstance() {
        return new PhotoFrameDataFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_frame_data_fragment, container, false);
//        myActivity = this.getActivity();
        myTextView = view.findViewById(R.id.test_text_view);
//        myTextView.setText(myActivity.getMyData());
        if(savedInstanceState != null) {
            myTextView.setText(savedInstanceState.getString("character"));
        }
        Bundle args = getArguments();
        if(args != null) {
            character = args.getString("character");
        }
        myTextView.setText(character);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PhotoFrameDataViewModel.class);
        // TODO: Use the ViewModel
        if(savedInstanceState != null) {
            character = savedInstanceState.getString("character");
            myTextView.setText(savedInstanceState.getString("character"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("character",character);
    }



}
