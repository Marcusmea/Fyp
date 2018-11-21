package com.example.marcus.fyp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BedDoor1Activity extends AppCompatActivity {

    int [] imagebedroom = new int[] {R.drawable.lock , R.drawable.unlock};
    ImageView lockbedroom1;
    int c = 0;
    TextView mValueView;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference refHome = firebaseDatabase.getReference("Home");
    DatabaseReference refBedroomDoor, refBedroommotor;
    DatabaseReference database= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_door1);

        refBedroomDoor = refHome.child("BedroomDoor");
        refBedroommotor = refBedroomDoor.child("motor");


        mValueView = (TextView) findViewById(R.id.bedroomvalueview);
        lockbedroom1 =(ImageView)findViewById(R.id.imageViewBedroom);

        database.child("VibrateBedroom").addValueEventListener(new ValueEventListener() {
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

        final Button lock = findViewById(R.id.lockbut3);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refBedroommotor.setValue(false);
                Toast.makeText(BedDoor1Activity.this, "Door is Closed ! ", Toast.LENGTH_SHORT).show();
                lockbedroom1.setImageResource(imagebedroom[c]);
                if (c<1)
                    c++;
            }
        });

        final Button unlock = findViewById(R.id.unlockbut3);
        unlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refBedroommotor.setValue(true);
                Toast.makeText(BedDoor1Activity.this, "Door is Opened ! ", Toast.LENGTH_SHORT).show();

                lockbedroom1.setImageResource(imagebedroom[c]);
                if (c>0)
                    c--;
            }
        });
    }
}
