package com.example.marcus.fyp;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView profileemail, profilePhone,profileSeries;
    DatabaseReference databaseUser;
    ListView listViewUser;
    List<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        /* final TextView profileUser = (TextView) view.findViewById(R.id.tvProfileUserID);
         profileemail = (TextView) findViewById(R.id.tvProfileEmail);
         profilePhone = (TextView) findViewById(R.id.tvProfilephone);
         profileSeries = (TextView)findViewById(R.id.tvProfileseries); */

        databaseUser= FirebaseDatabase.getInstance().getReference("User");

        listViewUser = (ListView)findViewById(R.id.listViewUsers);

        userList=new ArrayList<>();

    }

    @Override
    public void onStart() {
        super.onStart();
        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){
                   // userList.clear();
                    User user= userSnapshot.getValue(User.class);

                    userList.add(user);
                }
                UserList adapter= new UserList(MainActivity.this, userList);
                listViewUser.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    }






