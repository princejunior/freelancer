package com.example.freelance.SecondFragmentFiles;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freelance.R;
import com.example.freelance.createContent.ContentView;
import com.example.freelance.createContent.DisplayAllContent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    RecyclerView recycleProfileTop;
    RecyclerView recyclerViewBottom;

    DatabaseReference databaseReferenceTop;
    DatabaseReference databaseReferenceBottom;

    ContentView contentViewTop;
    UserContentView userContentView;

//    ArrayList<DisplayAllContent> listTop;
    ArrayList<UserContent> listTop;

    ArrayList<UserContent> listBottom;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        recycleProfileTop = view.findViewById(R.id.recycleProfileTop);
        databaseReferenceTop = FirebaseDatabase.getInstance().getReference("User_Content");
        recycleProfileTop.setHasFixedSize(true);
        recycleProfileTop.setLayoutManager(new LinearLayoutManager(getActivity()));

        listTop = new ArrayList<>();
        userContentView = new UserContentView(getActivity(), listTop);
        recycleProfileTop.setAdapter(userContentView);

        databaseReferenceTop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    UserContent userContent = dataSnapshot.getValue(UserContent.class);
                    listTop.add(userContent);
                    Log.i(null, "USERS CONTENT!!!!! " + userContent.getDescription());
//                    DisplayAllContent displayAllContent = dataSnapshot.getValue(DisplayAllContent.class);
//                    listTop.add(displayAllContent);
                }
                userContentView.notifyDataSetChanged();

//                contentViewTop.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        // Inflate the layout for this fragment
        return view;

    }
}