package com.example.freelance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
//import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
//    Database database;
//    private Button uploadButton_Upload;
//    private ProgressBar progressBar_Upload;
//    private ImageView imageView_Upload;
//    private Uri imageUri_Upload;
//    MenuItem homeFragment = (MenuItem) findViewById(R.id.homeFragment);
//    MenuItem profileFragment = (MenuItem) findViewById(R.id.profileFragment);
//    MenuItem addFragment = (MenuItem) findViewById(R.id.addFragment);


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.homeFragment:
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
       database.getUser("JH62fdRA5ANwoJx4lDd4c0zYQ6B3");
//       database.uploadUserCreatedContent();

    }





}