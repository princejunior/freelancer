package com.example.freelance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
//import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.freelance.FirstFagmentFiles.FirstFragment;
import com.example.freelance.SecondFragmentFiles.SecondFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.homeFragment:
//                            selectedFragment = null;
                            selectedFragment = new FirstFragment();
                            break;
                        case R.id.profileFragment:
                            selectedFragment = new SecondFragment();
                            break;
                        case R.id.addFragment:
                            selectedFragment = new ThirdFragment();
                            break;
                    }
                fragmentManager.beginTransaction()
                            .replace(R.id.fragmentView, selectedFragment, null)
                            .setReorderingAllowed(true)
                            .commit();
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentView,selectedFragment).commit();
                    return true;
                }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Database database = new Database();
        setContentView(R.layout.activity_main);

//      Displays Bottom Navigation Bar
        BottomNavigationView bottomNavigationView1= (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView1.setOnItemSelectedListener(navListener);
        bottomNavigationView1.bringToFront();

        database.createUser("JH62fdRA5ANwoJx4lDd4c0zYQ6B3", "Naruto","Uzamaki", "hdkjfhhsd fkj dsfkjh dhfs kdf");
//        database.getUser("JH62fdRA5ANwoJx4lDd4c0zYQ6B3");
//       database.uploadUserCreatedContent();

    }





}