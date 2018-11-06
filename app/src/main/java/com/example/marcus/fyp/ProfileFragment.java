package com.example.marcus.fyp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends  Fragment{

   // private TextView profileID, profileEmail, profilephone,profileseriesno;
   // private FirebaseDatabase firebaseDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        //final TextView profileUser = (TextView) view.findViewById(R.id.tvProfileUser);
        final TextView profileemail = (TextView) view.findViewById(R.id.tvProfileEmail);
        final TextView profilePhone = (TextView) view.findViewById(R.id.tvProfilephone);
        final TextView profileSeries = (TextView) view.findViewById(R.id.tvProfileseries);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( @NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                profileemail.setText(String.format("Email: %s", user.getEmail()));
                profilePhone.setText("Phone Number: " + user.getPhoneNumber());
                profileSeries.setText("Series NO: " + user.getseriesNO());
            }

            @Override
            public void onCancelled( @NonNull DatabaseError databaseError) {
               // Toast.makeText(ProfileFragment.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}
