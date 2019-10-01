package com.example.uni_framedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.uni_framedata.ui.photo.PhotoFrameDataFragment;
import com.example.uni_framedata.ui.textless.TextlessFrameDataFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class CharacterFrameDataActivity extends AppCompatActivity{
    private String character = "";
    private Fragment frag1 = new PhotoFrameDataFragment();
    private Fragment frag2 = new TextlessFrameDataFragment();
    private Fragment active = frag1;
    private FragmentManager myFm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_frame_data);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            character = bundle.getString("Character");
        }
        if(savedInstanceState!=null){
//            if(!frag1.isAdded()) {
            frag1 = getSupportFragmentManager()
                    .findFragmentByTag("PhotoFrag");
//            }
//            if(!frag2.isAdded()) {
            frag2 = getSupportFragmentManager()
                    .findFragmentByTag("TextFrag");
//            }
            active = getSupportFragmentManager()
                    .findFragmentByTag("Active");
        } else {
            Bundle newBundle = new Bundle();
            newBundle.putString("character", character);
            frag1.setArguments(newBundle);
            frag2.setArguments(newBundle);
        }
        BottomNavigationView navView = findViewById(R.id.simplicity_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener);
        myFm.beginTransaction().add(R.id.activity_frame_data,frag2).hide(frag2).commit();
        myFm.beginTransaction().add(R.id.activity_frame_data,frag1).commit();

    }
    private BottomNavigationView.OnNavigationItemSelectedListener myOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch(menuItem.getItemId()){
                        case R.id.framedata_only_photo:
                            myFm.beginTransaction().hide(active).show(frag1).commit();
                            active = frag1;
                            return true;
                        case R.id.framedata_only_text:
                            myFm.beginTransaction().hide(active).show(frag2).commit();
                            active = frag2;
                            return true;
                    }
                    return false;
                }
            };
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        getSupportFragmentManager().putFragment(outState, "PhotoFrag", frag1);
        getSupportFragmentManager().putFragment(outState, "TextFrag", frag2);
        getSupportFragmentManager().putFragment(outState, "Active", active);
    }
}
