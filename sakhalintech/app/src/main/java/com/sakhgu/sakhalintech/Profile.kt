package com.sakhgu.sakhalintech

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class Profile : AppCompatActivity() {

    lateinit var back: ImageView
    lateinit var logout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        back = findViewById(R.id.exit)
        logout = findViewById(R.id.logout)

        back.setOnClickListener(){
            finish()
        }
        logout.setOnClickListener(){
            val intent = Intent(this@Profile, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }
}