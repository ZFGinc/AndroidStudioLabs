package com.zfginc.calculator

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var Num1: EditText
    private lateinit var Num2: EditText

    private lateinit var Result: TextView

    private lateinit var btnAdd: Button
    private lateinit var btnSub: Button
    private lateinit var btnMult: Button
    private lateinit var btnDiv: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Num1 = findViewById(R.id.Num1);
        Num2 = findViewById(R.id.Num2);

        Result = findViewById(R.id.Result);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);

        btnAdd.setOnClickListener(){
            Calculate(btnAdd)
        }
        btnSub.setOnClickListener(){
            Calculate(btnSub)
        }
        btnMult.setOnClickListener(){
            Calculate(btnMult)
        }
        btnDiv.setOnClickListener(){
            Calculate(btnDiv)
        }
    }

    private fun Calculate(button : View){
        var num1 = 0f
        var num2 = 0f
        var result = 0f
        var operation = ""

        if (TextUtils.isEmpty(Num1.getText().toString())
            || TextUtils.isEmpty(Num2.getText().toString())) {
            return;
        }

        num1 = Num1.getText().toString().toFloat();
        num2 = Num2.getText().toString().toFloat();

        when (button.getId()) {
            R.id.btnAdd -> {
                operation = "+";
                result = num1 + num2;
            }
            R.id.btnSub -> {
                operation = "-";
                result = num1 - num2;
            }
            R.id.btnMult -> {
                operation = "*";
                result = num1 * num2;
            }
            R.id.btnDiv -> {
                operation = "/";

                if (num2==0f) {
                    Result.setText("Can't count");
                    return
                }

                result = num1 / num2;
            }
            else -> {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
            }
        }

            Result.setText(num1.toString() + " " + operation + " " + num2.toString() + " = " + result.toString());
    }
}