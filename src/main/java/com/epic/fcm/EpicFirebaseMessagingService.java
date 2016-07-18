package com.epic.fcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by dhanushi_s on 7/11/2016.
 */
public abstract class EpicFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.

        if (remoteMessage.getNotification() != null) {
            this.getFCMMessageHandler().onNotificationReceived(remoteMessage.getNotification());
        }
        else{
            if (remoteMessage.getFrom().startsWith("/topics"))
                this.getFCMMessageHandler().onSubscribedDataMessageReceived(remoteMessage);
        }
    }
    
    public abstract FCMMessageHandler getFCMMessageHandler();

}
