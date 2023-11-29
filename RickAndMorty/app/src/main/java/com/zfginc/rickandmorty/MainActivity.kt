package com.zfginc.rickandmorty

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private val client = OkHttpClient()
    private val pages = "https://rickandmortyapi.com/api/character?page="

    private var serach_name = false

    private lateinit var characters: GridView
    private lateinit var left_button: Button
    private lateinit var center_button: Button
    private lateinit var rigth_button: Button
    private lateinit var start_button: Button
    private lateinit var end_button: Button
    private lateinit var back_all_pages: Button

    private lateinit var search_input: EditText

    private var current_page: Int = 1
    private var max_page: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characters = findViewById(R.id.idGVcourses)
        left_button = findViewById(R.id.left_button)
        center_button = findViewById(R.id.center_button)
        rigth_button = findViewById(R.id.rigth_button)
        start_button = findViewById(R.id.start_button)
        end_button = findViewById(R.id.end_button)
        search_input = findViewById(R.id.search_input)
        back_all_pages = findViewById(R.id.back_all_pages)

        //Footer buttons
        start_button.setOnClickListener(){
            current_page=1

            updateFooterButtons()
            ShowPage(current_page)
        }
        left_button.setOnClickListener(){
            if(current_page - 1 < 1) return@setOnClickListener

            if(current_page==max_page) current_page--
            current_page--

            updateFooterButtons()
            ShowPage(current_page)
        }
        center_button.setOnClickListener(){
            if(current_page == 1)
                current_page++

            if(current_page == max_page)
                current_page--

            updateFooterButtons()
            ShowPage(current_page)
        }
        rigth_button.setOnClickListener(){
            if(current_page +1 > max_page) return@setOnClickListener

            if(current_page==1) current_page++
            current_page++

            updateFooterButtons()
            ShowPage(current_page)
        }
        end_button.setOnClickListener(){
            current_page = max_page

            updateFooterButtons()
            ShowPage(current_page)
        }
        //

        back_all_pages.setOnClickListener(){
            current_page = 1
            serach_name = false
            search_input.setText("")

            ShowPage(current_page)
            updateFooterButtons()

            back_all_pages.visibility = View.GONE
        }

        search_input.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                current_page = 1
                back_all_pages.visibility = View.VISIBLE
                serach_name = true
                ShowPage(current_page)
                return@OnKeyListener true
            }
            false
        })

        ShowPage(current_page)
    }

    private fun ShowPage(num: Int){
        val charactersModelArrayList: ArrayList<CharacterModel> = ArrayList<CharacterModel>()

        var url = pages+current_page.toString()
        if(serach_name) url = pages+num.toString()+"&name="+search_input.text

        Log.e("url", url)

        val request = Request.Builder()
            .url(url)
            .build()

        Thread {
            val json = client.newCall(request).execute()
                .use { response -> JSONObject(response.body!!.string()) }

            if(json.has("error")){
                max_page = 0
                runOnUiThread() {
                    Toast.makeText(this, json.getString("error"), Toast.LENGTH_LONG).show()
                }
            } else {
                val info = json.getJSONObject("info")
                max_page = info.getInt("pages")

                val result = json.getJSONArray("results")

                for(i in 0 until result.length()){
                    val character = result.getJSONObject(i)
                    charactersModelArrayList.add(
                        CharacterModel(
                            character.getInt("id"),
                            character.getString("name"),
                            character.getString("image")
                        )
                    )
                }
            }

            val adapter = CharacterGVAdapter(this, charactersModelArrayList)

            runOnUiThread() {
                characters.adapter = adapter
                updateFooterButtons()
            }
        }.start()
    }

    fun about(v: View) {
        val myIntent = Intent(this@MainActivity, AboutCharacter::class.java)
        myIntent.putExtra("idcharacter", v.tag.toString().toInt())
        startActivity(myIntent)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun updateFooterButtons(){
        if(max_page < 4) {

            left_button.text = "1"
            center_button.text = "2"
            rigth_button.text = "3"

            when (max_page) {
                1 -> {
                    rigth_button.visibility = View.GONE
                    center_button.visibility = View.GONE
                }
                2 -> {
                    rigth_button.visibility = View.GONE
                    center_button.visibility = View.VISIBLE
                }
                3 -> {
                    rigth_button.visibility = View.VISIBLE
                    center_button.visibility = View.VISIBLE
                }
                else -> {
                    left_button.visibility = View.GONE
                    rigth_button.visibility = View.GONE
                    center_button.visibility = View.GONE
                }
            }

            when (current_page) {
                1 -> {
                    left_button.background = resources.getDrawable(R.drawable.custom_current_button)
                    center_button.background = resources.getDrawable(R.drawable.custom_button)
                    rigth_button.background = resources.getDrawable(R.drawable.custom_button)
                }
                2 -> {
                    left_button.background = resources.getDrawable(R.drawable.custom_button)
                    center_button.background = resources.getDrawable(R.drawable.custom_current_button)
                    rigth_button.background = resources.getDrawable(R.drawable.custom_button)
                }
                3 -> {
                    left_button.background = resources.getDrawable(R.drawable.custom_button)
                    center_button.background = resources.getDrawable(R.drawable.custom_button)
                    rigth_button.background = resources.getDrawable(R.drawable.custom_current_button)
                }
                else -> {
                }
            }
        }
        else {
            left_button.visibility = View.VISIBLE
            rigth_button.visibility = View.VISIBLE
            center_button.visibility = View.VISIBLE

            if (current_page == 1) {
                left_button.background = resources.getDrawable(R.drawable.custom_current_button)
                center_button.background = resources.getDrawable(R.drawable.custom_button)
                rigth_button.background = resources.getDrawable(R.drawable.custom_button)

                left_button.text = "1"
                center_button.text = "2"
                rigth_button.text = "3"
            }

            if (current_page > 1 && current_page < max_page) {
                left_button.background = resources.getDrawable(R.drawable.custom_button)
                center_button.background = resources.getDrawable(R.drawable.custom_current_button)
                rigth_button.background = resources.getDrawable(R.drawable.custom_button)

                left_button.text = (current_page - 1).toString()
                center_button.text = current_page.toString()
                rigth_button.text = (current_page + 1).toString()
            }


            if (current_page == max_page) {
                left_button.background = resources.getDrawable(R.drawable.custom_button)
                center_button.background = resources.getDrawable(R.drawable.custom_button)
                rigth_button.background = resources.getDrawable(R.drawable.custom_current_button)

                left_button.text = (max_page - 2).toString()
                center_button.text = (max_page - 1).toString()
                rigth_button.text = max_page.toString()
            }
        }
    }
}