package com.example.marcus.fyp;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MasterRoomActivity extends AppCompatActivity {


    private Button unlock;
    private Button lock;
    TextView mValueView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference refHome = firebaseDatabase.getReference("Home");
    DatabaseReference refMasterDoor, refMastermotor;
    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_room);

        refMasterDoor = refHome.child("MasterDoor");
        refMastermotor = refMasterDoor.child("motor");

        TextView textstatus;
        ToggleButton toggleButton;
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toggleButton.setTextOff("Switch On");
        toggleButton.setTextOn("Switch Off");

        //unlock = (Button) findViewById(R.id.unlockbut2);
        //lock = (Button) findViewById(R.id.lockbut2);

       // controlUnlock(refDoor, unlock);
       // controlLock(refDoor, lock);
       // controlled(refLight,toggleButton);
        controlmasterdoor(refMastermotor, toggleButton);
       // textstatus = (TextView) findViewById(R.id.status);
        mValueView = (TextView) findViewById(R.id.fdvalueview);
       // buttonstatus(refLight, textstatus);

        database.child("Vibrate Master").addValueEventListener(new ValueEventListener() {
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
    }

    private void controlmasterdoor(final DatabaseReference refmotor1,final ToggleButton togggle_btnmaster){
        togggle_btnmaster.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                refmotor1.setValue(isChecked);
            }
        });
        refmotor1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean status_door=(Boolean)dataSnapshot.getValue();
                togggle_btnmaster.setChecked(status_door);
                if(status_door) {
                    togggle_btnmaster.setTextOn("Switch Off");
                }else {
                    togggle_btnmaster.setTextOff("Switch On");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    /*
    private void controldoor(final DatabaseReference refmotor1,final ToggleButton togggle_btn){
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
*/
        private void buttonstatus(final DatabaseReference button_a, final TextView textbuttonstatus){
        button_a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Boolean button_status=(Boolean)dataSnapshot.getValue();
                textbuttonstatus.setText(button_status.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void jumpToNotificationMaster(View view){
        Intent goToNotificationMaster = new Intent(this,NotificationMasterRoom.class);
        startActivity(goToNotificationMaster);
    }
}
