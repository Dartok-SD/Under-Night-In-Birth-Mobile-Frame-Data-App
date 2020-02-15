package com.example.uni_framedata.ui.photo;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.uni_framedata.CharacterFrameDataActivity;
import com.example.uni_framedata.LoadCharacterFrameData;
import com.example.uni_framedata.R;

public class PhotoFrameDataFragment extends Fragment {

    private PhotoFrameDataViewModel mViewModel;
    private Activity myActivity;
    private TextView myTextView;
    private String character = "";

    //    private Boolean foundChara = false;
    public static PhotoFrameDataFragment newInstance() {
        return new PhotoFrameDataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photo_frame_data_fragment, container, false);
//        myActivity = this.getActivity();
//        myTextView = view.findViewById(R.id.test_text_view);
//        myTextView.setText(myActivity.getMyData());
        myActivity = getActivity();
        Bundle args = getArguments();
        if(args != null) {
            character = args.getString("character");
        }
        init(view);
        if(savedInstanceState != null) {

        }
        return view;
    }

    public void init(View view){
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayoutProduct);
        TableRow tb_row0 = new TableRow(this.getContext());
//        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.MATCH_PARENT);
        TableLayout.LayoutParams layParams =
                new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
        layParams.setMargins(0,0,0,0);
        tb_row0.setLayoutParams(layParams);

        TextView tv_0 = new TextView(this.getContext());
        tv_0.setText(character);
        tv_0.setTextColor(Color.BLACK);
//        tv_0.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1 ) );
        tb_row0.addView(tv_0);
        LoadCharacterFrameData characterFrames = new LoadCharacterFrameData(character.toLowerCase(),this.getContext());

        for(int i = 0; i < characterFrames.getMoves().size(); i++) {
            TableRow tb_row = new TableRow(this.getContext());
            LoadCharacterFrameData.MoveData thisMove = characterFrames.getMoves().get(i);

            ImageView img = new ImageView(this.getContext());
            int resource = myActivity.getResources().getIdentifier("@drawable/" + character.toLowerCase() + thisMove.getName().toLowerCase().replaceAll("[^a-zA-Z0-9]", "")
                    , null, myActivity.getPackageName());
            img.setImageResource(resource);
            tb_row.addView(img);
            tableLayout.addView(tb_row, layParams);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PhotoFrameDataViewModel.class);
        // TODO: Use the ViewModel
        if(savedInstanceState != null) {
            character = savedInstanceState.getString("character");
//            myTextView.setText(savedInstanceState.getString("character"));
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("character",character);
    }



}
