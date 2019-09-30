package com.example.uni_framedata.ui.textless;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.uni_framedata.R;

public class TextlessFrameDataFragment extends Fragment {

    private TextlessFrameDataViewModel mViewModel;
    private String character = "";
    private TableRow mTableRow;

    public static TextlessFrameDataFragment newInstance() {
        return new TextlessFrameDataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.textless_frame_data_fragment, container, false);
        Bundle args = getArguments();
        if(args != null) {
            character = args.getString("character");
        }
        init(view);
        return view;
    }
    public void init(View view){
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayoutProduct);
        TableRow tb_row0 = new TableRow(this.getContext());
//
//        TextView tv = new TextView(this.getContext());
//        tv.setText(" Where is this? ");
//        tb_row0.addView(tv);

        TextView tv_0 = new TextView(this.getContext());
        tv_0.setText(" Move ");
        tb_row0.addView(tv_0);

        TextView tv_1 = new TextView(this.getContext());
        tv_1.setText(" Damage ");
        tb_row0.addView(tv_1);

        TextView tv_2 = new TextView(this.getContext());
        tv_2.setText(" Startup ");
        tb_row0.addView(tv_2);

        TextView tv_3 = new TextView(this.getContext());
        tv_3.setText(" Active ");
        tb_row0.addView(tv_3);

        TextView tv_4 = new TextView(this.getContext());
        tv_4.setText(" Recovery ");
        tb_row0.addView(tv_4);

        TextView tv_5 = new TextView(this.getContext());
        tv_5.setText(" Frame Adv ");
        tb_row0.addView(tv_5);

        TextView tv_6 = new TextView(this.getContext());
        tv_6.setText(" Cancel ");
        tb_row0.addView(tv_6);

        TextView tv_7 = new TextView(this.getContext());
        tv_7.setText(" Guard ");
        tb_row0.addView(tv_7);

        TextView tv_8 = new TextView(this.getContext());
        tv_8.setText(" Recovery ");
        tb_row0.addView(tv_8);

        tableLayout.addView(tb_row0);

        for(int i = 0; i < 3; i++){

        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TextlessFrameDataViewModel.class);

        // TODO: Use the ViewModel
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("character",character);
    }

}
