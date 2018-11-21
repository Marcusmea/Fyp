package com.example.marcus.fyp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.app.Service.START_STICKY;

public class IService extends Service  {

    private final String CHANNEL_ID = "personal_notifications";
    private final int NOTIFICATION_ID = 001;

    private final String CHANNEL_IDmaster = "personal_notifications";
    private final int NOTIFICATION_IDmaster = 002;

    private final String CHANNEL_IDbedrroom = "personal_notifications";
    private final int NOTIFICATION_IDbedroom = 003;

    private final String CHANNEL_IDmotion = "personal_notifications";
    private final int NOTIFICATION_IDmotion = 004;


    public IService() {
    }

    NotificationManagerCompat notificationManager;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager= NotificationManagerCompat.from(this);

        database.child("Vibrate").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    String value = noteDataSnapshot.getValue(String.class);
                    if (value.equals("1")) {
                        displayNotification();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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

        database.child("VibrateBedroom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot:dataSnapshot.getChildren()){
                    String value= noteDataSnapshot.getValue(String.class);
                    if(value.equals("1")){
                        displayNotificationBedroom();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database.child("Motion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot noteDataSnapshot:dataSnapshot.getChildren()){
                    String value= noteDataSnapshot.getValue(String.class);
                    if(value.equals("1")){
                        displayNotificationmotion();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void displayNotification(){
        createNotificationChannel();

        Intent landingIntent = new Intent(this, FrontDoorActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.lock);
        builder.setContentTitle(" Alert !!! Vibration Detected !");
        builder.setContentText(" There is a strong vibration detected on the Front Door");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(landingPendingIntent);
        // this is to set the sound when the notification pop out with the default ringtone of the phone
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setAutoCancel(false);
        // this is to set the time for the phone vibration
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 });
        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_ID,builder.build());
    }
    private  void createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name= "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name,importance);

            notificationChannel.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void displayNotificationMaster(){
        createNotificationChannelMaster();


        Intent landingIntent = new Intent(this, MasterRoomActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_IDmaster);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Alert !!! Vibration Detected !");
        builder.setContentText("There is a strong vibration detected on the Master Room Door");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(landingPendingIntent);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 });
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


    public void displayNotificationBedroom(){
        createNotificationChannelBedroom();

        Intent landingIntent = new Intent(this, BedDoor1Activity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_IDbedrroom);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Alert !!! Vibration Detected !");
        builder.setContentText(" Strong vibration detected on the Bedroom Door");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 });
        builder.setContentIntent(landingPendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_IDbedroom,builder.build());

    }

    private  void createNotificationChannelBedroom()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name= "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannelBedroom = new NotificationChannel(CHANNEL_IDbedrroom,name,importance);

            notificationChannelBedroom.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannelBedroom);
        }
    }

    public void displayNotificationmotion(){
        createNotificationChannelmotion();

        Intent landingIntent = new Intent(this, FrontDoorActivity.class);
        landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent landingPendingIntent = PendingIntent.getActivity(this,0,landingIntent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_IDmotion);
        builder.setSmallIcon(R.drawable.ic_sms_notification);
        builder.setContentTitle("Teck Kian is houdee");
        builder.setContentText("Teck Kian is really houdee");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000 });
        builder.setContentIntent(landingPendingIntent);
        builder.setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICATION_IDmotion,builder.build());

    }

    private  void createNotificationChannelmotion()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            CharSequence name= "Personal Notifications";
            String description = "Include all the personal notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannelMotion = new NotificationChannel(CHANNEL_IDbedrroom,name,importance);

            notificationChannelMotion.setDescription(description);

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannelMotion);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
