package com.ai.covidainuas.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.ai.covidainuas.fragment.AboutFragment;
import com.ai.covidainuas.fragment.HomeFragment;
import com.ai.covidainuas.fragment.IdnFragment;
import com.ai.covidainuluas.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Menampilkan Fragment Summary Ketika App Dibuka
        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_frame,homeFragment)
                .commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    //Menu Navigasi Bawah

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //Ke Fragment Summary
            case R.id.homeMenu:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame,homeFragment)
                        .commit();
                return true;

            //Ke Fragment Idn
            case R.id.indoMenu:
                IdnFragment idnFragment = new IdnFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame,idnFragment)
                        .commit();
                return true;
            case R.id.aboutMenu:
                AboutFragment aboutFragment = new AboutFragment();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame,aboutFragment)
                        .commit();
                return true;

            //ke Fragment News
        }
        return false;
    }


}