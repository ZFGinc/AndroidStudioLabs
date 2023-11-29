package com.zfginc.simplesignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

private const val USERNAME = "admin"
private const val PASSWORD = "admin"

class MainActivity : AppCompatActivity() {

    private lateinit var login_button: Button;

    private lateinit var login_google: ImageButton;
    private lateinit var login_facebook: ImageButton;
    private lateinit var login_twitter: ImageButton;

    private lateinit var fogot_password: TextView;

    private lateinit var username_input: EditText;
    private lateinit var password_input: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button = findViewById(R.id.login_button);
        login_google = findViewById(R.id.login_google);
        login_facebook = findViewById(R.id.login_facebook);
        login_twitter = findViewById(R.id.login_twitter);
        fogot_password = findViewById(R.id.forgot_password);
        username_input = findViewById(R.id.username);
        password_input = findViewById(R.id.password);

        login_button.setOnClickListener(){
            login();
        }

        fogot_password.setOnClickListener(){
            showToast("usr/pas - admin/admin")
        }
    }

    private fun showToast(id: Int){
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun login(){
        var username = username_input.getText().toString()
        var password = password_input.getText().toString()

        if(username == "" || password == "") {
            showToast(R.string.none_field)
            return
        }

        if(username == USERNAME && password == PASSWORD)
            showToast(R.string.correct_login)
        else
            showToast(R.string.uncorrect_login)
    }
}