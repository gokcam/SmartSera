package com.gok.smartsera.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.gok.smartsera.R
import com.google.firebase.auth.FirebaseAuth

class anaMenu : AppCompatActivity() {
    private  lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ana_menu)
        auth = FirebaseAuth.getInstance()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val manuInflater = menuInflater
        menuInflater.inflate(R.menu.secenekler_menusu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.cikis_yap)
        {
            auth.signOut() // Firebase Çıkış yapması için
            val intent = Intent(this, MainActivity::class.java) // çıkış yap dediğinde nereye dönmesini istersek
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun pil(view: View)
    {

        val intent = Intent(this, pilActivity::class.java)
        startActivity(intent)

    }
    fun nemsicaklik(view: View)
    {

        val intent = Intent(this, NemSicaklikActivity::class.java)
        startActivity(intent)

    }
    fun bocek(view: View)
    {

        val intent = Intent(this, bocekActivity::class.java)
        startActivity(intent)

    }

}