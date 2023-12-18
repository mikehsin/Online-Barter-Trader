package com.example.csci3130project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    FirebaseDatabase database;
    private Button mBtnSearch,mBtnViewProfile, mBtnAddPreference, mBtnLogout;
    private TextView tvWeclomeToHome;
    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);

        tvWeclomeToHome = findViewById(R.id.tvWelcome_Homepage);
        Bundle bundle = getIntent().getExtras();
        tvWeclomeToHome.setText("Welcome to Online Barter Trader, \t"+bundle.getString("username"));

        etSearch = findViewById(R.id.etSearch);
        String SearchMsg = etSearch.getText().toString().trim();

        mBtnSearch = findViewById(R.id.btnSearch);
        mBtnSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentToSearchResult = new Intent(HomePageActivity.this, SearchResultPage.class);
                intentToSearchResult.putExtra("searchMsg",SearchMsg);
                startActivity(intentToSearchResult);
            }
        });

        mBtnViewProfile = findViewById(R.id.btnViewProfile);
        mBtnViewProfile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentToProfile = new Intent(HomePageActivity.this, ProfilePage.class);
                startActivity(intentToProfile);
            }
        });

        mBtnAddPreference = findViewById(R.id.btnAddPreference);
        mBtnAddPreference.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToPreference = new Intent(HomePageActivity.this, PreferencePage.class);
                Bundle bundle = getIntent().getExtras();
                intentToPreference.putExtra("username", bundle.getString("username"));//send username to other activity
                startActivity(intentToPreference);
            }
        });

        //find button log out
        mBtnLogout = findViewById(R.id.but_Logout);
        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
                finish();
                System.exit(0);

            }
        });

    }

    @Override
    protected void onResume() {
        //will be executed onResume
        super.onResume();
    }

}