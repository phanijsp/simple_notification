package com.example.simple_notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.Notification.DEFAULT_ALL;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button sendbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);
        sendbutton = (Button) findViewById(R.id.send);
        final int[] i = {0};
        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationChannel channel = null;
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    channel = new NotificationChannel("my_channel_01",
                            "Channel human readable title",
                            NotificationManager.IMPORTANCE_HIGH);
                     mNotificationManager.createNotificationChannel(channel);
                }
                Notification notification =
                        new NotificationCompat.Builder(getApplicationContext(), "notify_001")
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle("Message : ")
                                .setContentText(editText.getText().toString())
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setDefaults(NotificationCompat.DEFAULT_SOUND)
                                .setChannelId("my_channel_01").build();
                mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(i[0], notification);
                i[0]++;
            }
        });
    }

}
