package com.example.uni_framedata.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
//import com.squareup.picasso.Callback;
//import com.squareup.picasso.Picasso;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uni_framedata.CharacterFrameDataActivity;
import com.example.uni_framedata.R;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> characterNames;
    private Activity myActivity;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ImageView mImageView;
        public MyViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.character_name);
            mImageView = (ImageView) v.findViewById(R.id.character_plate);
        }

    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset, Activity activity) {
        characterNames = myDataset;
        myActivity = activity;
    }
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.charactercards, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//        holder.mImageView.setText("Button");
        File filepath = new File("/../../../../../src/main/assets/CharacterPlates/"
                +characterNames.get(position)+".png");
        String testFilePath = "CharacterPlates/"
                + characterNames.get(position) + ".png";
        int resource = myActivity.getResources().getIdentifier("@drawable/"+characterNames.get(position).toLowerCase()+ "_plate",null,myActivity.getPackageName());
        holder.mImageView.setImageResource(resource);
        holder.mTextView.setText(characterNames.get(position));
        holder.mTextView.setTextColor(Color.BLACK);
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#AD5DD0"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#CD60FC"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
        final int tempPosition = position;
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle characterBundle = new Bundle();
                characterBundle.putString("Character",characterNames.get(tempPosition));
                Intent newIntent = new Intent(myActivity, CharacterFrameDataActivity.class);
                newIntent.putExtras(characterBundle);
                myActivity.startActivity(newIntent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return characterNames.size();
    }
}
