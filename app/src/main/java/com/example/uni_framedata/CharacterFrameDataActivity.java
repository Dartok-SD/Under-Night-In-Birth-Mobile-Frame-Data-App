package com.example.uni_framedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
    private final static String TAG  = "TEST";
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
        //        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
        //                R.id.framedata_only_photo, R.id.framedata_only_text)
        //                .build();
        //        NavController navController = Navigation.findNavController(this, R.id.simplicity_nav_host);
        //        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        //        NavigationUI.setupWithNavController(navView, navController);
//        myFm.beginTransaction().add(R.id.activity_frame_data,frag1);
//        if(savedInstanceState == null){
            myFm.beginTransaction().hide(frag2).commit();

            myFm.beginTransaction().show(frag1).commit();
            active = frag1;
//        }
        navView.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener);
        Log.i(TAG,"onCreate");
//        active = frag1;
    }
    @Override
    protected void onStart(){
        super.onStart();
        myFm.beginTransaction().hide(frag2).show(frag1).commit();
        active = frag1;
        loadFragment(frag1);
        Log.i(TAG,"onStart");
    }
    private BottomNavigationView.OnNavigationItemSelectedListener myOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch(menuItem.getItemId()){
                        case R.id.framedata_only_photo:
                            System.out.printf("Does the listener even trigger? %d\n", menuItem.getItemId());
                            myFm.beginTransaction().hide(active).show(frag1).commit();
                            active = frag1;
                            loadFragment(frag1);
                            return true;
                        case R.id.framedata_only_text:
                            System.out.printf("Does the listener even trigger? %d\n", menuItem.getItemId());
                            myFm.beginTransaction().hide(active).show(frag2).commit();
                            active = frag2;
                            loadFragment(frag2);
                            return true;
                        default:
                            System.out.printf("Does the listener even trigger? %d\n", menuItem.getItemId());
                            active = frag1;
                            myFm.beginTransaction().show(active).commit();
                            loadFragment(frag1);
                            return true;
                    }
//                    return false;
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
//    private void setFragment(Fragment fragment) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.replace(R.id.main_frame,fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//
//    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        //HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.simplicity_nav_host, fragment,null);
        // transaction.addToBackStack(null);
        /* Comment this line and it should work!*/
        //transaction.addToBackStack(null);
        Bundle newBundle = new Bundle();
        newBundle.putString("character", character);
        fragment.setArguments(newBundle);
        transaction.commit();
    }
}
