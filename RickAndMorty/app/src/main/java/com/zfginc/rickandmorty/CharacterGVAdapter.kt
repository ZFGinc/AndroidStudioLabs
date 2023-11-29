package com.zfginc.rickandmorty

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