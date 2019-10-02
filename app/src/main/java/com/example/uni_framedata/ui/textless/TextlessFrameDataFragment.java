package com.example.uni_framedata.ui.textless;

import androidx.lifecycle.ViewModelProviders;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.uni_framedata.DatabaseTable;
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
        if(savedInstanceState == null){
            init(view);
        }
        return view;
    }
    public void init(View view){
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.tableLayoutProduct);
        TableRow tb_row0 = new TableRow(this.getContext());
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        tb_row0.setLayoutParams(lp);

        TextView tv_0 = new TextView(this.getContext());
        tv_0.setText(" Move ");
        tv_0.setTextColor(Color.BLACK);
        tb_row0.addView(tv_0);

        TextView tv_1 = new TextView(this.getContext());
        tv_1.setText(" Damage ");
        tv_1.setTextColor(Color.BLACK);
        tb_row0.addView(tv_1);

        TextView tv_2 = new TextView(this.getContext());
        tv_2.setText(" Startup ");
        tv_2.setTextColor(Color.BLACK);
        tb_row0.addView(tv_2);

        TextView tv_3 = new TextView(this.getContext());
        tv_3.setText(" Active ");
        tv_3.setTextColor(Color.BLACK);
        tb_row0.addView(tv_3);

        TextView tv_4 = new TextView(this.getContext());
        tv_4.setText(" Recovery ");
        tv_4.setTextColor(Color.BLACK);
        tb_row0.addView(tv_4);

        TextView tv_5 = new TextView(this.getContext());
        tv_5.setText(" Frame Adv ");
        tv_5.setTextColor(Color.BLACK);
        tb_row0.addView(tv_5);

        TextView tv_6 = new TextView(this.getContext());
        tv_6.setText(" Cancel ");
        tv_6.setTextColor(Color.BLACK);
        tb_row0.addView(tv_6);

        TextView tv_7 = new TextView(this.getContext());
        tv_7.setText(" Guard ");
        tv_7.setTextColor(Color.BLACK);
        tb_row0.addView(tv_7);

        TextView tv_8 = new TextView(this.getContext());
        tv_8.setText(" Recovery ");
        tv_8.setTextColor(Color.BLACK);
        tb_row0.addView(tv_8);

        tableLayout.addView(tb_row0);

        DatabaseTable db = new DatabaseTable(this.getContext());
        Cursor c = db.getCharacterMatches(character,null);
        for(int i = 0; i < 3; i++){

        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(TextlessFrameDataViewModel.class);
        if(savedInstanceState != null) {
        }

        // TODO: Use the ViewModel
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("character",character);
    }

}
