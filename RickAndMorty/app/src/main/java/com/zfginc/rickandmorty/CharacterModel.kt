package com.zfginc.rickandmorty

class CharacterModel(private var id: Int, private var name: String, private var img: String) {
    fun getId(): Int = id
    fun getName(): String = name
    fun getImg(): String = img
}