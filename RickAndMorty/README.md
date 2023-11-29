<p align = "center">МИНИСТЕРСТВО НАУКИ И ВЫСШЕГО ОБРАЗОВАНИЯ
РОССИЙСКОЙ ФЕДЕРАЦИИ
ФЕДЕРАЛЬНОЕ ГОСУДАРСТВЕННОЕ БЮДЖЕТНОЕ
ОБРАЗОВАТЕЛЬНОЕ УЧРЕЖДЕНИЕ ВЫСШЕГО ОБРАЗОВАНИЯ
«САХАЛИНСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ»</p>
<br><br><br><br><br><br>
<p align = "center">Институт естественных наук и техносферной безопасности<br>Кафедра информатики<br>Хроменков Владимир Александрович</p>
<br><br><br>

<p align = "center">Лабораторная работа №12<br>«Каталог персонажей»<br>01.03.02 Прикладная математика и информатика</p>
<br><br><br><br><br><br><br><br><br><br><br><br>
<p align = "right">Научный руководитель<br>
Соболев Евгений Игоревич</p>
<br><br><br>
<p align = "center">г. Южно-Сахалинск<br>2023 г.</p>

***
# <p align = "center">Оглавление</p>
- [Цели и задачи](#цели-и-задачи)
- [Решение задач](#решение-задач)
- [Вывод](#вывод)

***

# <p align = "center">Цели и задачи</p>

Требуется разработать мобильное приложение на Kotlin для поиска информации о персонажах из вселенной Рика и Морти.Нужные вам данные о персонажах можно взять <a href="https://rickandmortyapi.com/">тут</a>.

1.  ФУНКЦИОНАЛЬНЫЕ ТРЕБОВАНИЯ 1:

    - Экран с поисковой строкой;
    - Отображение информации о персонаже (имя, раса, пол, статус);
    - Вся информация о персонажах задаётся внутри программы.
Может пригодиться: <a href="https://pastebin.com/pCiLJ9qt">пример организации данных.</a>
 
2.	ФУНКЦИОНАЛЬНЫЕ ТРЕБОВАНИЯ 2:

    - Отдельный экран с информацией о персонаже;
    - Отображение расширенной информации о персонаже (изображение, родная планета, местоположение).
    - Вся информация о персонажах задается внутри программы.

3. ФУНКЦИОНАЛЬНЫЕ ТРЕБОВАНИЯ 2:
    - Использовать <a href="https://rickandmortyapi.com/">RickAndMortyAPI</a> для поддержания актуальности информации о персонажах.

***

# <p align = "center">Решение задач</p>

Главная страница приложения будет содержать вверху строку поиска, снизу кнопки для переключения страниц, а в центре будут отображаться блоки с персонажами и краткой информацией `Изображение` и `Имя`.

<p align = "center">
    <img src = "images/1-1.png">
    <img src = "images/1-2.png">
    <br>
    <img src = "images/1-3.png">
</p>

Вот код главной страницы:

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@color/gray_black"
    tools:context=".MainActivity">

    <LinearLayout
        android:id="@+id/header"
        android:layout_width="match_parent"
        android:layout_height="70dp"
        android:layout_alignParentTop="true"
        android:layout_marginTop="0dp"
        android:orientation="horizontal"
        android:background="@color/block"
        android:gravity="center">

        <Button
            android:id="@+id/back_all_pages"
            android:layout_width="55dp"
            android:layout_height="55dp"
            android:layout_marginLeft="32dp"
            android:background="@drawable/custom_current_button"
            app:backgroundTint="@null"
            android:textSize="20sp"
            android:text="@string/back"
            android:visibility="gone"/>
        
        <EditText
            android:id="@+id/search_input"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:padding="8dp"
            android:layout_marginHorizontal="32dp"

            android:background="@drawable/custom_input"
            android:drawableStart="@drawable/search_icon"
            android:drawableTint="@color/white"
            android:drawablePadding="16dp"

            android:hint="@string/search"
            android:maxLines="1"
            android:inputType="text"

            android:textAlignment="textStart"
            android:textColor="@color/white"
            android:textColorHint="@color/white"
            android:textSize="22sp" />

    </LinearLayout>

    <GridView
        android:id="@+id/idGVcourses"
        android:layout_width="match_parent"
        android:layout_height="507dp"

        android:layout_above="@+id/footer"
        android:layout_below="@+id/header"
        android:layout_marginBottom="0dp"
        android:horizontalSpacing="6dp"
        android:numColumns="2"
        android:padding="8dp"
        android:scrollbars="vertical"
        android:verticalSpacing="6dp" />

    <LinearLayout
        android:id="@+id/footer"
        android:layout_width="match_parent"
        android:layout_height="80dp"
        android:layout_alignParentBottom="true"
        android:orientation="horizontal"
        android:background="@color/gray_normal"
        android:gravity="center" >

        <Button
            android:id="@+id/start_button"
            android:layout_width="55dp"
            android:layout_height="55dp"
            android:layout_marginHorizontal="4dp"
            android:background="@drawable/custom_button"
            app:backgroundTint="@null"
            android:textSize="16sp"
            android:text="@string/prev"/>
        <Button
            android:id="@+id/left_button"
            android:layout_width="55dp"
            android:layout_height="55dp"
            android:layout_marginHorizontal="4dp"
            android:background="@drawable/custom_current_button"
            app:backgroundTint="@null"
            android:textSize="16sp"
            android:text="1"/>
        <Button
            android:id="@+id/center_button"
            android:layout_width="55dp"
            android:layout_height="55dp"
            android:layout_marginHorizontal="4dp"
            android:background="@drawable/custom_button"
            app:backgroundTint="@null"
            android:textSize="16sp"
            android:text="2"/>
        <Button
            android:id="@+id/rigth_button"
            android:layout_width="55dp"
            android:layout_height="55dp"
            android:layout_marginHorizontal="4dp"
            android:background="@drawable/custom_button"
            app:backgroundTint="@null"
            android:textSize="16sp"
            android:text="3"/>
        <Button
            android:id="@+id/end_button"
            android:layout_width="55dp"
            android:layout_height="55dp"
            android:layout_marginHorizontal="4dp"
            android:background="@drawable/custom_button"
            app:backgroundTint="@null"
            android:baselineAligned="false"
            android:textSize="16sp"
            android:text="@string/next"/>

    </LinearLayout>

</RelativeLayout>
```

Вот код блока с краткой информацией:
```xml
<?xml version="1.0" encoding="utf-8"?><!-- XML implementation of Card Layout -->
<androidx.cardview.widget.CardView
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="240dp"
    android:layout_gravity="center"
    android:layout_margin="5dp"
    app:cardCornerRadius="5dp"
    app:cardElevation="5dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="240dp"
        android:orientation="vertical"
        android:padding="8dp">

        <ImageView
            android:id="@+id/image"
            android:layout_width="140dp"
            android:layout_height="140dp"
            android:layout_gravity="center"
            android:src="@mipmap/ic_launcher" />

        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"
            android:text="@string/about"
            android:textAlignment="center"
            android:textSize="18sp" />

        <TextView
            android:id="@+id/about"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="4dp"
            android:text="@string/about"
            android:textAlignment="center"
            android:textColor="@color/green_200"
            android:onClick="about"
            android:tag="1"
            android:textSize="20sp" />
    </LinearLayout>
</androidx.cardview.widget.CardView>
```

<p align = "center">
    <img src = "images/2-1.png">
    <img src = "images/2-2.png">
</p>

Организация данных:

`CharacterModel.kt`
```kotlin
class CharacterModel(private var id: Int, private var name: String, private var img: String) {
    fun getId(): Int = id
    fun getName(): String = name
    fun getImg(): String = img
}
```

`CharacterGVAdapter.kt`
```kotlin
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load

class CharacterGVAdapter(context: Context, courseModelArrayList: ArrayList<CharacterModel>) :
    ArrayAdapter<CharacterModel?>(context, 0, courseModelArrayList as List<CharacterModel?>) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var listitemView = convertView
        if (listitemView == null) {
            listitemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)!!
        }

        val characterModel: CharacterModel = getItem(position)!!
        val title = listitemView.findViewById<TextView>(R.id.title)
        val about = listitemView.findViewById<TextView>(R.id.about)
        val image = listitemView.findViewById<ImageView>(R.id.image)

        title.text = characterModel.getName()
        about.tag = characterModel.getId().toString()
        image.load(characterModel.getImg())

        return listitemView
    }
}
```

Работа главной страницы организована в `MainActivity.kt`
```kotlin
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
```

Работа страницы подробной информации о персонаже организована в `AboutCharacter.kt`

<p align = "center">
    <img src = "images/3-1.png">
    <img src = "images/3-2.png">
</p>

```kotlin
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
```

***

# <p align = "center">Вывод</p>

Выполнив *лабораторную работу №12*, совершенствую навыки работы со средой разработки `Android Studion`, работы с языком `Kotlin` построение API запросов и их обработку в больших количествах. 