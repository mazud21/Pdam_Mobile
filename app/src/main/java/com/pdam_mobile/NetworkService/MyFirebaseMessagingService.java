package com.pdam_mobile.NetworkService;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.pdam_mobile.Local.SharedPrefManager;
import com.pdam_mobile.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    static String CHANNEL_ID = "EXAMPLE_CHANNEL_ID";

    private static final String TAG = "MyFirebaseIIDService";
    private SharedPreferences sharedPreferences;

    @Override
    public void onNewToken(String s) {
        sendRegistrationToServer(s);
    }

    private void sendRegistrationToServer(String strRefreshedToken) {
        //You can implement this method to store the token on your server
        //Not required for current project
        sharedPreferences = getSharedPreferences(SharedPrefManager.SP_PDAM_APP, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SharedPrefManager.FIREBASE_NOTIF_TOKEN, strRefreshedToken);
        editor.apply();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        sendNotification(
                remoteMessage.getData().get("title"),
                remoteMessage.getData().get("body"),
                remoteMessage.getData().get("clickAction"));
    }

    public static void sendNotification(String messageTitle, String messageBody, String clickAction) {
        Intent intent = new Intent(clickAction);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(MainApplication.context, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MainApplication.context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_pdam_bantul)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) MainApplication.context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
