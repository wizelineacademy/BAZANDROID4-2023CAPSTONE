package mx.jramon.subias.dbmovieproyect.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

private val gson = Gson()

fun Any?.toJson(): String = gson.toJson(this)

fun Any?.toJsonElement(): JsonElement? = gson.toJsonTree(this)

fun <T> String?.toModel(model: Class<T>): T = gson.fromJson(this, model)

fun String?.toJsonElement(): JsonElement = gson.toJsonTree(this)

fun String?.toJsonObject(): JsonObject? = gson.toJsonTree(this).asJsonObject

fun String.convertStringToJsonObject(): JsonObject? {
    return try {
        Gson().fromJson(this, JsonObject::class.java)
    }catch (e: Exception){
        null
    }
}

