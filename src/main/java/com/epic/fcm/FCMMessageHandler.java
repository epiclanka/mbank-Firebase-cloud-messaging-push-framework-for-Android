package com.epic.fcm;

import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.messaging.RemoteMessage.Notification;

/**
 * Created by dhanushi_s on 7/18/2016.
 */
public interface FCMMessageHandler {

    void onNotificationReceived(Notification notification);

    void onSubscribedDataMessageReceived(RemoteMessage remoteMessage);
}
