package com.sakhgu.sakhalintech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class QRcodePage : AppCompatActivity() {
    lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_code_actuvity)

        back = findViewById(R.id.back_button)

        back.setOnClickListener(){finish()}
    }
}