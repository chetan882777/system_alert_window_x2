package com.chetan.system_alert_window_x2.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.content.LocusId;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import com.chetan.system_alert_window_x2.BubbleActivity;

import static com.chetan.system_alert_window_x2.utils.Constants.INTENT_EXTRA_PARAMS_MAP;
import static java.lang.System.currentTimeMillis;

public class NotificationHelper {
    private static final String CHANNEL_ID = "bubble_notification_channel";
    private static final String CHANNEL_NAME = "Incoming notification";
    private static final String CHANNEL_DESCRIPTION = "Incoming notification description";
    private static final String SHORTCUT_LABEL = "Notification";
    private static final int BUBBLE_NOTIFICATION_ID = 1237;
    private static final String BUBBLE_SHORTCUT_ID = "bubble_shortcut";
    private static final int REQUEST_CONTENT = 1;
    private static final int REQUEST_BUBBLE = 2;
    private static NotificationManager notificationManager;
    private static final String TAG = "NotificationHelper";
    private Context mContext;

    private static NotificationHelper mInstance;

    private NotificationHelper(Context context) {
        this.mContext = context;
        if (isMinAndroidQ())
            initNotificationManager();
    }

    public static NotificationHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotificationHelper(context);
        }
        return mInstance;
    }

    private boolean isMinAndroidQ() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q;
    }

    private boolean isMinAndroidR() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void initNotificationManager() {
        if (notificationManager == null) {
            if (mContext == null) {
                Log.e(TAG, "Context is null. Can't show the System Alert Window");
                return;
            }
            notificationManager = mContext.getSystemService(NotificationManager.class);
            setUpNotificationChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setUpNotificationChannels() {
        if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void updateShortcuts(Icon icon) {
        Set<String> categories = new LinkedHashSet<>();
        categories.add("com.example.android.bubbles.category.TEXT_SHARE_TARGET");
        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(mContext, BUBBLE_SHORTCUT_ID)
                .setLocusId(new LocusId(BUBBLE_SHORTCUT_ID))
                //.setActivity(new ComponentName(mContext, BubbleActivity.class))
                .setShortLabel(SHORTCUT_LABEL)
                .setIcon(icon)
                .setLongLived(true)
                .setCategories(categories)
                .setIntent(new Intent(mContext, BubbleActivity.class).setAction(Intent.ACTION_VIEW))
                .setPerson(new Person.Builder()
                        .setName(SHORTCUT_LABEL)
                        .setIcon(icon)
                        .build())
                .build();
        ShortcutManager shortcutManager = (ShortcutManager) mContext.getSystemService(Context.SHORTCUT_SERVICE);
        shortcutManager.pushDynamicShortcut(shortcutInfo);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Notification.BubbleMetadata createBubbleMetadata(Icon icon, PendingIntent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            return new Notification.BubbleMetadata.Builder(intent, icon)
                    .setDesiredHeight(250)
                    .setAutoExpandBubble(true)
                    .setSuppressNotification(true)
                    .build();
        } else {
            return new Notification.BubbleMetadata.Builder()
                    .setDesiredHeight(250)
                    .setIcon(icon)
                    .setIntent(intent)
                    .setAutoExpandBubble(true)
                    .setSuppressNotification(true)
                    .build();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void showNotification(Icon icon, String notificationTitle, String notificationBody, HashMap<String, Object> params) {
        if (isMinAndroidR())
            updateShortcuts(icon);
        Person user = new Person.Builder().setName("You").build();
        Person person = new Person.Builder().setName(notificationTitle).setIcon(icon).build();
        Intent bubbleIntent = new Intent(mContext, BubbleActivity.class);
        bubbleIntent.setAction(Intent.ACTION_VIEW);
        bubbleIntent.putExtra(INTENT_EXTRA_PARAMS_MAP, params);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, REQUEST_BUBBLE, bubbleIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long now = currentTimeMillis() - 100;
        Notification.Builder builder = new Notification.Builder(mContext, CHANNEL_ID)
                .setBubbleMetadata(createBubbleMetadata(icon, pendingIntent))
                .setContentTitle(notificationTitle)
                .setSmallIcon(icon)
                .setCategory(Notification.CATEGORY_MESSAGE)
                .setShortcutId(BUBBLE_SHORTCUT_ID)
                .setLocusId(new LocusId(BUBBLE_SHORTCUT_ID))
                .addPerson(person)
                .setShowWhen(true)
                .setContentIntent(PendingIntent.getActivity(mContext, REQUEST_CONTENT, bubbleIntent, PendingIntent.FLAG_UPDATE_CURRENT))
                .setStyle(new Notification.MessagingStyle(user)
                        .addMessage(new Notification.MessagingStyle.Message(notificationBody, now, person))
                        .setGroupConversation(false))
                .setWhen(now);
        if(isMinAndroidR()){
            builder.addAction(new Notification.Action.Builder(null, "Click the icon in the end ->", null).build());
        }
        notificationManager.notify(BUBBLE_NOTIFICATION_ID, builder.build());
    }

    public void dismissNotification(){
        notificationManager.cancel(BUBBLE_NOTIFICATION_ID);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public boolean areBubblesAllowed(){
        if(isMinAndroidR()) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(CHANNEL_ID, BUBBLE_SHORTCUT_ID);
            assert notificationChannel != null;
            return notificationManager.areBubblesAllowed() ||notificationChannel.canBubble();
        }else{
            int devOptions = Settings.Secure.getInt(mContext.getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0);
            if (devOptions == 1) {
                Log.d(TAG, "Android bubbles are enabled");
                return true;
            } else {
                Log.e(TAG, "System Alert Window will not work without enabling the android bubbles");
                Toast.makeText(mContext, "Enable android bubbles in the developer options, for System Alert Window to work", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

}
