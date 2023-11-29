package com.zfginc.simplesignin

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

private const val EMAIL = "admin@gmail.com"
private const val PASSWORD = "admin"

class MainActivity : AppCompatActivity() {

    private val emailSuggestions = arrayOf("gmail.com", "gmail.uk.com", "yahoo.com", "hotmail.com", "mail.ru","bk.ru","yandex.ru")

    private lateinit var login_button: Button
    private lateinit var fogot_password: TextView

    private lateinit var email_input: EditText
    private lateinit var password_input: EditText

    private lateinit var error_email: TextView
    private lateinit var error_password: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button = findViewById(R.id.login_button)
        fogot_password = findViewById(R.id.forgot_password)
        email_input = findViewById(R.id.email)
        password_input = findViewById(R.id.password)
        error_email = findViewById(R.id.error_email)
        error_password = findViewById(R.id.error_password)

        login_button.setOnClickListener {
            login()
        }

        fogot_password.setOnClickListener {
            startActivity(Intent(this@MainActivity, Recovery::class.java))
        }

        email_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var input = email_input.text.toString()
                if (input.contains("@")) {
                    val index = input.indexOf("@")
                    val domain = input.substring(index + 1)

                    if(domain in emailSuggestions)
                        error_email.text = ""
                    else
                        error_email.text = getString(R.string.email_not_valid)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        password_input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var input = password_input.text.toString()

                if(isPasswordValid(input))
                    error_password.text = ""
                else
                    error_password.text = getString(R.string.password_length)

            }

            override fun afterTextChanged(s: Editable?) {}
        })

    }

    private fun showToast(id: Int){
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }
    private fun showToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    private fun login(){
        val email = email_input.getText().toString()
        val password = password_input.getText().toString()

        if(email == "" || password == "") {
            showToast(R.string.none_field)
            return
        }

        if(!isEmailValid(email)){
            showToast(R.string.email_not_valid)
            return
        }

        if(!isPasswordValid(password)){
            showToast(R.string.password_length)
            return
        }

        if(email == EMAIL && password == PASSWORD)
            showToast(R.string.correct_login)
        else
            showToast(R.string.uncorrect_login)
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        if(password.length < 3 || password.length > 32) return false
        return true
    }
}