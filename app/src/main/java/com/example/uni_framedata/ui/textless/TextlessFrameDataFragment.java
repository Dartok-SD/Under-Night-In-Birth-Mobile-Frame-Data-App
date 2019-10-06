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
import com.example.uni_framedata.LoadCharacterFrameData;
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
        TableLayout.LayoutParams layParams =
                new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);

        layParams.setMargins(0,0,0,0);
        tb_row0.setLayoutParams(layParams);

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

//        TextView tv_8 = new TextView(this.getContext());
//        tv_8.setText(" Recovery ");
//        tv_8.setTextColor(Color.BLACK);
//        tb_row0.addView(tv_8);

        TextView tv_9 = new TextView(this.getContext());
        tv_9.setText(" Invicibility ");
        tv_9.setTextColor(Color.BLACK);
        tb_row0.addView(tv_9);

        TextView tv_10 = new TextView(this.getContext());
        tv_10.setText(" Attribute ");
        tv_10.setTextColor(Color.BLACK);
        tb_row0.addView(tv_10);

        tableLayout.addView(tb_row0,layParams);
//
//        DatabaseTable db = new DatabaseTable(this.getContext());
//        Cursor c = db.getCharacterMatches(character,null);
        LoadCharacterFrameData characterFrames = new LoadCharacterFrameData(character.toLowerCase(),this.getContext());
        for(int i = 0; i < characterFrames.getMoves().size(); i++){

            TableRow tb_row = new TableRow(this.getContext());
//            TableRow.MarginLayoutParams lp1 = new TableRow.MarginLayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
//            lp1.setMargins(0,0,0,40);
//            TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
////            TableRow.MarginLayoutParams params = new TableRow.MarginLayoutParams(
////                    TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
////            params.setMargins(10, 10, 10,
////                    10);
            TableLayout.LayoutParams layoutParams =
                    new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                            TableLayout.LayoutParams.WRAP_CONTENT);

            layoutParams.setMargins(10,10,10,10);
            if(i == characterFrames.getMoves().size()-1){
                layoutParams.setMargins(10,10,10,210);
            }

//            tr.setLayoutParams(lp);
            tb_row.setLayoutParams(layoutParams);

            LoadCharacterFrameData.MoveData thisMove = characterFrames.getMoves().get(i);

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

//            TextView tvf_8 = new TextView(this.getContext());
//            tvf_8.setText(thisMove.getRecovery());
//            tvf_8.setTextColor(Color.BLACK);
//            tb_row.addView(tvf_8);

            TextView tvf_9 = new TextView(this.getContext());
            tvf_9.setText(thisMove.getInvincibility());
            tvf_9.setTextColor(Color.BLACK);
            tb_row.addView(tvf_9);

            TextView tvf_10 = new TextView(this.getContext());
            tvf_10.setText(thisMove.getAttribute());
            tvf_10.setTextColor(Color.BLACK);
            tb_row.addView(tvf_10);

            tableLayout.addView(tb_row,layoutParams);
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
