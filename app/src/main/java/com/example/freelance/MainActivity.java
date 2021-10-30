package com.example.freelance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.fragment.app.Fragment;
//import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    Fragment selectedFragment = null;
                    switch (item.getItemId()){
                        case R.id.firstFragment:
                            selectedFragment = new FirstFragment();
                            break;
                        case R.id.secondFragment:
                            selectedFragment = new SecondFragment();
                            break;
                        case R.id.thirdFragment:
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
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(navListener);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.fragmentView, FirstFragment.class, null)
//                .setReorderingAllowed(true)
//                .commit();
//        NavController navController = Navigation.findNavController(R.id.fragmentView);

//        bottomNavigationView.setup
//        AppBarConfiguration appBarConfiguration = AppBarConfiguration(setOf(R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment));

//        Toast.makeText(MainActivity.this,"Firebase connection Success", Toast.LENGTH_LONG).show();
//
//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(null, "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(null, "Failed to read value.", error.toException());
//            }
//        });
    }

}