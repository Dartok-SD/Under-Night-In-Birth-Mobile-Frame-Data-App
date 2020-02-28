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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.uni_framedata.CharacterFrameDataActivity;
import com.example.uni_framedata.LoadCharacterFrameData;
import com.example.uni_framedata.R;
import com.squareup.picasso.*;

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
            if(resource == 0){
                resource = myActivity.getResources().getIdentifier("@drawable/" + character.toLowerCase() + LoadCharacterFrameData.tryDifferentName(thisMove.getName())
                        , null, myActivity.getPackageName());
            }
            if(resource == 0){
                resource = myActivity.getResources().getIdentifier("@drawable/" + character.toLowerCase() + LoadCharacterFrameData.convertInput(thisMove.getInput())
                        , null, myActivity.getPackageName());
            }
            if(resource == 0){
                // default image
                resource = myActivity.getResources().getIdentifier("@drawable/default"+character.toLowerCase()
                        , null, myActivity.getPackageName());
                System.out.printf("Default picture Default Name: %s, was it found %d\n", "default"+character.toLowerCase(), resource);
            }

            Picasso.with(this.getContext()).load(resource).resize(0,200).into(img);
//            img.setImageResource(resource);
//            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100, 100);
//            img.setLayoutParams(layoutParams);
            tb_row.addView(img);

            TextView tvf_0 = new TextView(this.getContext());
            tvf_0.setText(thisMove.getName());
            tvf_0.setTextColor(Color.BLACK);
            tb_row.addView(tvf_0);
            TextView tvf_1 = new TextView(this.getContext());
            tvf_1.setText(thisMove.getDamage());
            tvf_1.setTextColor(Color.BLACK);

            tb_row.addView(tvf_1);

            TextView tvf_2 = new TextView(this.getContext());
            tvf_2.setText(thisMove.getStartup());
            tvf_2.setTextColor(Color.BLACK);

            tb_row.addView(tvf_2);

            TextView tvf_3 = new TextView(this.getContext());
            tvf_3.setText(thisMove.getActive());
            tvf_3.setTextColor(Color.BLACK);

            tb_row.addView(tvf_3);

            TextView tvf_4 = new TextView(this.getContext());
            tvf_4.setText(thisMove.getRecovery());
            tvf_4.setTextColor(Color.BLACK);

            tb_row.addView(tvf_4);

            TextView tvf_5 = new TextView(this.getContext());
            tvf_5.setText(thisMove.getFrameadv());
            tvf_5.setTextColor(Color.BLACK);

            tb_row.addView(tvf_5);

            TextView tvf_6 = new TextView(this.getContext());
            tvf_6.setText(thisMove.getCancel());
            tvf_6.setTextColor(Color.BLACK);

            tb_row.addView(tvf_6);

            TextView tvf_7 = new TextView(this.getContext());
            tvf_7.setText(thisMove.getGuard());
            tvf_7.setTextColor(Color.BLACK);
            tb_row.addView(tvf_7);

            TextView tvf_8 = new TextView(this.getContext());
            tvf_8.setText(thisMove.getRecovery());
            tvf_8.setTextColor(Color.BLACK);
            tb_row.addView(tvf_8);

            TextView tvf_9 = new TextView(this.getContext());
            tvf_9.setText(thisMove.getInvincibility());
            tvf_9.setTextColor(Color.BLACK);
            tb_row.addView(tvf_9);

            TextView tvf_10 = new TextView(this.getContext());
            tvf_10.setText(thisMove.getAttribute());
            tvf_10.setTextColor(Color.BLACK);
            tb_row.addView(tvf_10);

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
