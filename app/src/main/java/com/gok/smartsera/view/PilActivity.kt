package com.gok.smartsera.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.gok.smartsera.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pil.*

class pilActivity : AppCompatActivity() {
    val CHANNEL_ID = "channel_id_example_01"
    val notificationId = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pil)
        createNotificationChannel()
        sendNotification ()

        var database = FirebaseDatabase.getInstance().reference
        var getData = object  : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                var degerPil = snapshot.child("yuzde").getValue()
                var deneme =  degerPil.toString()
                var deger = deneme.toFloat()
                if(deger <30 )
                {
                    createNotificationChannel()
                    sendNotification ()
                    textView.text = "Pil Yüzdesi= ${degerPil}"
                }

                else {
                    textView.text = "Pil Yüzdesi= ${degerPil}"
                }

            }



            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getData)
    }

    public fun createNotificationChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            val name = "Dikkat Pil Azalıyor"
            val uyari = "Pil %30 altına düştü."
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = uyari
            }
            val notificationManager : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
    public fun sendNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Dikkat pil azalıyor")
            .setContentText("Piliniz %30 altına düştü acil durum")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this))
        {
            notify(notificationId, builder.build())
        }


    }
}