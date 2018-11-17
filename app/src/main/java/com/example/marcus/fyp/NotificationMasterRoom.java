package com.example.marcus.fyp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class NotificationMasterRoom extends AppCompatActivity {

    private final String CHANNEL_IDmaster = "personal_notifications";
    private final int NOTIFICATION_IDmaster = 002;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationmasterrom);

        database.child("VibrateMaster").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot:dataSnapshot.getChildren()){
                    String value= noteDataSnapshot.getValue(String.class);
                    if(value.equals("1")){
                        displayNotificationMaster();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void displayNotificationMaster(){
        createNotificationChannelMaster();
        Toast.makeText(NotificationMasterRoom.this, "Notification for master room", Toast.LENGTH_SHORT).show();

        Intent landingIntent = new Intent(this, welcome_page.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_IDmaster);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Teck Kian very sohai");
        builder.setContentText("Teck Kian is really sohai now");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(landingPendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_IDmaster,builder.build());

    }

    private  void createNotificationChannelMaster()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name= "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannelMaster = new NotificationChannel(CHANNEL_IDmaster,name,importance);

            notificationChannelMaster.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannelMaster);
        }
    }
}
