package com.zfginc.simplesignin

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

private const val EMAIL = "admin@gmail.com"
private const val PASSWORD = "admin"

class Recovery : AppCompatActivity() {

    private lateinit var recovery_button: Button
    private lateinit var sign_in_text: TextView

    private lateinit var email_input: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        recovery_button = findViewById(R.id.recovery_button);
        email_input = findViewById(R.id.email);
        sign_in_text = findViewById(R.id.sign_in)

        recovery_button.setOnClickListener(){
            Recovery()

        }

        sign_in_text.setOnClickListener(){
            finish();
        }

    }

    private fun showToast(id: Int){
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun isEmailValid(email: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun Recovery(){
        var email = email_input.getText().toString()

        if(!isEmailValid(email)) {
            showToast(R.string.email_not_valid)
            return
        }

        if(email == EMAIL)
            showToast("Password: $PASSWORD")
        else
            showToast(R.string.not_found)
    }
}