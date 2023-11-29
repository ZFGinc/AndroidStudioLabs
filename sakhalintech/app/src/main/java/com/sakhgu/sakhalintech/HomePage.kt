package com.sakhgu.sakhalintech

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class HomePage : AppCompatActivity() {

    lateinit var qr_code_to: Button
    lateinit var icon_people: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        qr_code_to = findViewById(R.id.qr_code_to)
        icon_people = findViewById(R.id.icon_people)

        qr_code_to.setOnClickListener(){
            val intent = Intent(this, QRcodePage::class.java)
            startActivity(intent)
        }

        icon_people.setOnClickListener(){
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}