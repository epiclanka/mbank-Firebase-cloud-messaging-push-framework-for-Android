package com.epic.fcm;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dhanushi_s on 7/18/2016.
 */
public abstract class ContentMessageHandler implements FCMMessageHandler {
    public enum OPERATIONS{
        INSERT, DELETE, UPDATE, RESYNC;
    }
    @Override
    public void onNotificationReceived(RemoteMessage.Notification notification) {
                onNotification(notification);
    }

    @Override
    public void onSubscribedDataMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getFrom().startsWith("/topics/dbchange")){

            Object content    = remoteMessage.getData().get("content");
            JSONObject jsonObject2 = null;

            try {
                jsonObject2 =new JSONObject(content.toString());
                JSONArray jsonarray = jsonObject2.getJSONArray("ids");

                String operation = jsonObject2.get("operation").toString();
                String table    = jsonObject2.get("table").toString();

                switch (operation) {
                    case "DELETE":
                        onDbChange(table, OPERATIONS.DELETE, jsonarray);
                        break;
                    case "UPDATE":
                        onDbChange(table, OPERATIONS.UPDATE, jsonarray);
                        break;
                    case "INSERT":
                        onDbChange(table, OPERATIONS.INSERT, jsonarray);
                        break;
                    case "RESYNC":
                        onDbChange(table, OPERATIONS.RESYNC, jsonarray);
                        break;
                    default:

                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public abstract void onDbChange(String dbName,OPERATIONS operetion ,JSONArray jsonArrayIds );

    public abstract void onNotification(RemoteMessage.Notification notification);
}
