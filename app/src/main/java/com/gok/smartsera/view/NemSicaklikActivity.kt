package com.gok.smartsera.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gok.smartsera.R
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_nem_sicaklik.*

class NemSicaklikActivity : AppCompatActivity() {
    val paylasimMap = hashMapOf<String,Any>()
    private  lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nem_sicaklik)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser


        var database = FirebaseDatabase.getInstance().reference
        var getData = object  : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {

                var deger = snapshot.child("nem").getValue()
                var deger1 = snapshot.child("sicaklik").getValue()



                val db = Firebase.firestore

                val tarih = Timestamp.now()
                paylasimMap.put("tarih",tarih)
                paylasimMap.put("nem",deger.toString())
                paylasimMap.put("sicaklik",deger1.toString())


                db.collection("Paylasimlar").add(paylasimMap).addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {

                    }
                }.addOnFailureListener {

                }


                textView3.text="Nem Değeri:${deger} \n Sıcaklık Değeri:${deger1}"

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getData)
    }
}