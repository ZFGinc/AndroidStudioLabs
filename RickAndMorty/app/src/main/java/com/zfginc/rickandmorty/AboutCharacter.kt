package com.zfginc.rickandmorty

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import coil.load
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.util.*


class AboutCharacter : AppCompatActivity() {
    private val client = OkHttpClient()

    private lateinit var back_to_pages: Button

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var status_species: TextView
    private lateinit var gender: TextView
    private lateinit var location: TextView
    private lateinit var episode: TextView
    private lateinit var created: TextView

    private var id: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about_character)

        val mIntent = intent
        id = mIntent.getIntExtra("idcharacter", 0)

        back_to_pages = findViewById(R.id.back_to_pages)
        image = findViewById(R.id.image)
        name = findViewById(R.id.name)
        status_species = findViewById(R.id.status_species)
        gender = findViewById(R.id.gender)
        location = findViewById(R.id.location)
        episode = findViewById(R.id.episode)
        created = findViewById(R.id.created)

        back_to_pages.setOnClickListener(){
            finish()
        }

        ShowAbout()
    }

    @SuppressLint("SetTextI18n")
    private fun ShowAbout(){
        val url = "https://rickandmortyapi.com/api/character/$id"
        val request = Request.Builder()
            .url(url)
            .build()

        Thread {
            val json = client.newCall(request).execute()
                .use { response -> JSONObject(response.body!!.string()) }

            if(json.has("error")){
                runOnUiThread() {
                    Toast.makeText(this, json.getString("error"), Toast.LENGTH_LONG).show()
                }
            } else {
                val _name = json.getString("name").capitalize()
                val _status = json.getString("status").capitalize()
                val _species = json.getString("species").capitalize()
                val _gender = json.getString("gender").capitalize()
                val _image = json.getString("image")
                val _created = json.getString("created")

                val _location = json.getJSONObject("origin").getString("name")

                val episodes = json.getJSONArray("episode")
                val list_episodes: ArrayList<String> = ArrayList<String>()

                runOnUiThread() {
                    name.text = _name
                    status_species.text = "$_status - $_species"
                    gender.text = _gender
                    location.text = _location
                    created.text = _created.slice(0..9)
                    image.load(_image)
                    episode.text = "Loading..."

                    when(_status){
                        "Alive" -> status_species.compoundDrawables.getOrNull(0)?.setTint(Color.GREEN)
                        "Dead" -> status_species.compoundDrawables.getOrNull(0)?.setTint(Color.RED)
                        else -> status_species.compoundDrawables.getOrNull(0)?.setTint(Color.WHITE)
                    }
                }

                for(i in 0 until episodes.length()) {
                    val request_episode = Request.Builder()
                        .url(episodes.getString(i))
                        .build()

                    val _episode = client.newCall(request_episode).execute()
                        .use { response -> JSONObject(response.body!!.string()) }

                    list_episodes.add(_episode.getString("name"))
                }

                runOnUiThread() {
                    var str_episodes = ""

                    for(i in 0 until list_episodes.size){
                        str_episodes += "${i+1}. ${list_episodes[i]}\n"
                        episode.text = str_episodes
                    }
                }
            }
        }.start()
    }
}