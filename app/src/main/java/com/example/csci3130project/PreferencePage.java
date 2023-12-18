package com.example.csci3130project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class PreferencePage extends AppCompatActivity implements View.OnClickListener{
    FirebaseDatabase database;
    DatabaseReference userNameRef = null;
    DatabaseReference PreferenceRef = null;
    public static List<String> db_username = new ArrayList<>();
    public static List<String> db_userid = new ArrayList<>();
    public static List<String> db_Preference = new ArrayList<>();
    public String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_page);
        getSupportActionBar().setTitle("Update Preference");

        Button btnAddPreference1 = findViewById(R.id.appBtnPreference1);
        btnAddPreference1.setOnClickListener(this);

        Button btnAddPreference2 = findViewById(R.id.appBtnPreference2);
        btnAddPreference2.setOnClickListener(this);

        Button btnAddPreference3 = findViewById(R.id.appBtnPreference3);
        btnAddPreference3.setOnClickListener(this);

        Button btnAddPreference4 = findViewById(R.id.appBtnPreference4);
        btnAddPreference4.setOnClickListener(this);

        Button btnAddPreference5 = findViewById(R.id.appBtnPreference5);
        btnAddPreference5.setOnClickListener(this);

        Button btnAddPreference6 = findViewById(R.id.appBtnPreference6);
        btnAddPreference6.setOnClickListener(this);

        Button btnAddPreference7 = findViewById(R.id.appBtnPreference7);
        btnAddPreference7.setOnClickListener(this);

        Button btnAddPreference8 = findViewById(R.id.appBtnPreference8);
        btnAddPreference8.setOnClickListener(this);

        Button btnAddPreference9 = findViewById(R.id.appBtnPreference9);
        btnAddPreference9.setOnClickListener(this);

        Button btnAddPreference10 = findViewById(R.id.appBtnPreference10);
        btnAddPreference10.setOnClickListener(this);

        Button btnConfirmPreference = findViewById(R.id.appBtnPreferenceConfirm);
        btnConfirmPreference.setOnClickListener(this);

        Button btnCancelPreference = findViewById(R.id.appBtnPreferenceCancel);
        btnCancelPreference.setOnClickListener(this);

        //initiating the Firebase
        initializeDatabase();
        db_username = retrieveFirebaseDataValue(userNameRef, db_username);
        db_userid = retrieveFirebaseDataKey(PreferenceRef, db_userid);

    }

    protected void initializeDatabase() {
        //initialize database and reference field objects
        database = FirebaseDatabase.getInstance("https://csci3130-b92f1-default-rtdb.firebaseio.com/");
        userNameRef = database.getReference("Username");
        PreferenceRef = database.getReference("Preference");
    }

    protected List<String> retrieveFirebaseDataValue(DatabaseReference dataRef, List<String> db_list){
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
                while (itr.hasNext()) {
                    String db_data = (String) itr.next().getValue();
                    db_list.add(db_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PreferencePage.this, "Fail to read data", Toast.LENGTH_LONG).show();
            }
        });
        return db_list;
    }

    protected List<String> retrieveFirebaseDataKey(DatabaseReference dataRef, List<String> db_list){
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> itr = dataSnapshot.getChildren().iterator();
//                System.out.println(dataSnapshot.child(id).getValue());
                while (itr.hasNext()) {
                    String db_data = (String) itr.next().getKey();
                    db_list.add(db_data);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PreferencePage.this, "Fail to read data", Toast.LENGTH_LONG).show();
            }
        });
        return db_list;
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("username");
        id = db_userid.get(db_username.indexOf(text));

        switch (view.getId()) {
            case R.id.appBtnPreference1:
                if(db_Preference.contains("Car"))
                    db_Preference.remove("Car");
                else
                    db_Preference.add("Car");
                break;
            case R.id.appBtnPreference2:
                if(db_Preference.contains("Housing"))
                    db_Preference.remove("Housing");
                else
                    db_Preference.add("Housing");
                break;
            case R.id.appBtnPreference3:
                if(db_Preference.contains("Electronics"))
                    db_Preference.remove("Electronics");
                else
                 db_Preference.add("Electronics");
                break;
            case R.id.appBtnPreference4:
                if(db_Preference.contains("Furniture"))
                    db_Preference.remove("Furniture");
                else
                    db_Preference.add("Furniture");
                break;
            case R.id.appBtnPreference5:
                if(db_Preference.contains("Perfume"))
                    db_Preference.remove("Perfume");
                else
                    db_Preference.add("Perfume");
                break;
            case R.id.appBtnPreference6:
                if(db_Preference.contains("Toy"))
                    db_Preference.remove("Toy");
                else
                    db_Preference.add("Toy");
                break;
            case R.id.appBtnPreference7:
                if(db_Preference.contains("Sports"))
                    db_Preference.remove("Sports");
                else
                    db_Preference.add("Sports");
                break;
            case R.id.appBtnPreference8:
                if(db_Preference.contains("Pets"))
                    db_Preference.remove("Pets");
                else
                    db_Preference.add("Pets");
                break;
            case R.id.appBtnPreference9:
                if(db_Preference.contains("Textbook"))
                    db_Preference.remove("Textbook");
                else
                    db_Preference.add("Textbook");
                break;
            case R.id.appBtnPreference10:
                if(db_Preference.contains("Free"))
                    db_Preference.remove("Free");
                else
                    db_Preference.add("Free");
                break;
            case R.id.appBtnPreferenceConfirm:
                PreferenceRef.child(id).setValue(db_Preference.toString());
                Intent intent = new Intent(PreferencePage.this, HomePageActivity.class);
                intent.putExtra("username", bundle.getString("username"));
                startActivity(intent);
                break;
            case R.id.appBtnPreferenceCancel:
                Intent intentToCancel = new Intent(PreferencePage.this, HomePageActivity.class);
                intentToCancel.putExtra("username", bundle.getString("username"));
                startActivity(intentToCancel);
                break;
            default:break;
        }

    }

    @Override
    protected void onResume() {
        //will be executed onResume
        super.onResume();

        //clean all the array list object
        db_username.removeAll(db_username);
        db_userid.removeAll(db_userid);
        db_Preference.removeAll(db_Preference);
    }
}