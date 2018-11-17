package com.example.marcus.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class FrontDoorActivity extends AppCompatActivity {

    TextView mValueView, mValueView2;
    FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
    DatabaseReference refHome= firebaseDatabase.getReference("Home");
    DatabaseReference refDoor, refmotor;

    DatabaseReference database= FirebaseDatabase.getInstance().getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front_door);


        refDoor=refHome.child("Door");
        refmotor=refDoor.child("motor");

        TextView textstatus;
        ToggleButton toggleButton;
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton.setTextOff("Switch On");
        toggleButton.setTextOn("Switch Off");

        controlmotor(refmotor,toggleButton);
        textstatus = (TextView) findViewById(R.id.status1);
        mValueView = (TextView) findViewById(R.id.fdvalueview);
        mValueView2=(TextView) findViewById(R.id.fdvalueview2);
        buttonstatus(refmotor, textstatus);

        database.child("Vibrate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    String value = noteDataSnapshot.getValue(String.class);
                    mValueView.setText(value);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        database.child("Motion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot motionDataSnapshot: dataSnapshot.getChildren()){
                    String motion = motionDataSnapshot.getValue(String.class);
                    mValueView2.setText(motion);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void controlmotor(final DatabaseReference refMotor, final ToggleButton toggleButton ){
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                refMotor.setValue(isChecked);
            }
        });
        refMotor.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean status_door=(Boolean)dataSnapshot.getValue();
                toggleButton.setChecked(status_door);
                if(status_door) {
                    toggleButton.setTextOn("Switch Off");
                    Toast.makeText(FrontDoorActivity.this, "Door is Closed", Toast.LENGTH_SHORT).show();
                }else {
                    toggleButton.setTextOff("Switch On");
                    Toast.makeText(FrontDoorActivity.this, "Door is Opened", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void buttonstatus(final DatabaseReference button_b, final TextView textbuttonstatus){
        button_b.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean button_status=(Boolean)dataSnapshot.getValue();
                textbuttonstatus.setText(button_status.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void jumpToNotification(View view){
        Intent goToNotification = new Intent(this,Notification.class);
        startActivity(goToNotification);
    }
}
