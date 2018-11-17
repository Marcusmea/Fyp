
package com.example.marcus.fyp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends  Fragment {

    private TextView profileemail, profilePhone, profileSeries;
    DatabaseReference databaseUser;
    //ListView listViewUser;
   // List<User> userList;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final TextView profileUser = (TextView) view.findViewById(R.id.tvProfileUserID);
        final TextView profileemail = (TextView) view.findViewById(R.id.tvProfileEmail);
        final TextView profilePhone = (TextView) view.findViewById(R.id.tvProfilephone);
        final TextView profileSeries = (TextView) view.findViewById(R.id.tvProfileseries);


       // final FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference table_user = database.getReference("User");
        databaseUser= FirebaseDatabase.getInstance().getReference("User");

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){
                    User user= userSnapshot.getValue(User.class);
                  //  profileUser.setText().;
                    profileemail.setText("Email: "+user.getEmail());
                    profilePhone.setText("Phone Number: "+ user.getPhoneNumber());
                    profileSeries.setText("Series NO: " + user.getseriesNO());


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return view;
    }



}


       /* database.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot:dataSnapshot.getChildren()){
                    String value= noteDataSnapshot.getValue(String.class);
                    profileemail.setText(String.format("Email: %s", user.getEmail()));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }*/



