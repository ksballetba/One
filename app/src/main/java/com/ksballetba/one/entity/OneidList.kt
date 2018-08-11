package com.ksballetba.one.entity

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class OneidList(
        val res: Int, // 0
        val data: List<String>
){
    class Deserializer : ResponseDeserializable<OneidList> {
        override fun deserialize(content: String): OneidList? {
            return Gson().fromJson(content,OneidList::class.java)
        }
    }
}

