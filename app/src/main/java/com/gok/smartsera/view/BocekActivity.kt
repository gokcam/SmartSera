package com.gok.smartsera.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gok.smartsera.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_bocek.*

class bocekActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bocek)

        var database = FirebaseDatabase.getInstance().reference
        var getData = object  : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                var degerno = snapshot.child("photo").getValue()
                var yeni = degerno.toString()

                var deneme = "https://tr.wikipedia.org/wiki/Samsunspor#/media/Dosya:Samsunspor_logo_2.png"

                Picasso.get().load(deneme).into(imageView2)

                textView4.text="Böcek anlık photo=${yeni}"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getData)
    }
}