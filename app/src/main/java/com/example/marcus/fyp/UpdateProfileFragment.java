
package com.example.marcus.fyp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.marcus.fyp.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileFragment extends  Fragment {

    private TextView profileemail, profilePhone, profileSeries;
    DatabaseReference databaseUser;
    //ListView listViewUser;
   // List<User> userList;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final EditText profileUser = view.findViewById(R.id.tvProfileUserID);
        final EditText profileEmail = view.findViewById(R.id.tvProfileEmailUpdate);
        final EditText profilePhone = view.findViewById(R.id.tvProfilePhoneUpdate);
        final EditText profilePassword = view.findViewById(R.id.tvProfilePasswordUpdate);
        final EditText profileSeries = view.findViewById(R.id.tvProfileSeriesUpdate);
        final Button updateButton = view.findViewById(R.id.updateButton);

       // final FirebaseDatabase database = FirebaseDatabase.getInstance();
       // DatabaseReference table_user = database.getReference("User");
        databaseUser= FirebaseDatabase.getInstance().getReference("User");

        databaseUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot:dataSnapshot.getChildren()){
                    final User user= userSnapshot.getValue(User.class);
                  //  profileUser.setText().;
                    profileEmail.setText(user.getEmail());
                    profilePhone.setText(user.getPhoneNumber());
                    profilePassword.setText(user.getPassword());
                    profileSeries.setText(user.getseriesNO());

                    updateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatabaseReference emailRef = FirebaseDatabase.getInstance().getReference("User").child("email");
                            emailRef.setValue(profileEmail);
                        }
                    });
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



