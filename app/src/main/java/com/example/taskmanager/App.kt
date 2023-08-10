package com.example.taskmanager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.room.Room
import com.example.taskmanager.data.local.db.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()

       // if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
          //  val fcmChannel = NotificationChannel(
                //FCM_CHANNEL_ID,
                //"FCM_Channel",
                //NotificationManager.IMPORTANCE_HIGH
          //  )

           // val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

          //  manager.createNotificationChannel(fcmChannel)
       // }
    }
    companion object{
        lateinit var db: AppDatabase
        const val FCM_CHANNEL_ID = "FCM_CHANNEL_ID"

    }
}