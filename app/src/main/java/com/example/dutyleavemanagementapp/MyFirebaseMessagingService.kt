package com.example.dutyleavemanagementapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import android.widget.RemoteViews.RemoteView
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId="notification_channel"
const val channelName="package com.example.dutyleavemanagementapp"

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        if (remoteMessage.getNotification() != null) {

            genrateNotification(
                remoteMessage.notification!!.title!!,
                remoteMessage.notification!!.body!!
            )
        }
    }




    fun getRemoteView(tittle: String,message: String):RemoteViews{
        val remote=RemoteViews("com.example.dutyleavemanagementapp",R.layout.notifications)
        remote.setTextViewText(R.id.tttl,tittle)
        remote.setTextViewText(R.id.tttl1,message)
        remote.setImageViewResource(R.id.log111,R.drawable.img_logo)
        return remote
    }





    fun genrateNotification(tittle:String,message:String){
        val intent = Intent(this,MainActivity6::class.java,)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)
        var builder:NotificationCompat.Builder=NotificationCompat.Builder(applicationContext,
            channelId).setSmallIcon(R.drawable.img_logo).setAutoCancel(true).setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true).setContentIntent(pendingIntent)


        builder=builder.setContent(getRemoteView(tittle,message))
        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?


        if (Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.O
        ) {
            val notificationChannel = NotificationChannel(
                channelId,"web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager!!.createNotificationChannel(
                notificationChannel
            )
        }
        notificationManager!!.notify(0, builder.build())

    }
}