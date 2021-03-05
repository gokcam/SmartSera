package com.gok.smartsera.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.gok.smartsera.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val paylasimMap = hashMapOf<String,Any>()


    private  lateinit var auth: FirebaseAuth
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()





        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null)
        {
            val intent = Intent(this, anaMenu::class.java)
            startActivity(intent)
            finish()
        }



    }

    fun giris(view: View)
    {

        auth.signInWithEmailAndPassword(editTextEmail.text.toString(), editTextTextPassword.text.toString()).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val guncelKullanici = auth.currentUser?.email.toString()
                Toast.makeText(this, "Giriş başarılı ${guncelKullanici}", Toast.LENGTH_LONG).show()
                val intent = Intent(this, anaMenu::class.java)
                startActivity(intent)
                finish()
                paylasimMap.put("kullanici",guncelKullanici)
                db.collection("Paylasimlar").add(paylasimMap).addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {

                    }
                }.addOnFailureListener {

                }



            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()

        }
    }



}