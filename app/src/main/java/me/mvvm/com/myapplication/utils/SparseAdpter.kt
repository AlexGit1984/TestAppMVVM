package me.mvvm.com.myapplication.utils

import android.util.SparseArray
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import java.io.IOException


/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */

class SparseArrayTypeAdapter<T>(private val classOfT: Class<T>) : TypeAdapter<SparseArray<T>>() {

    private val gson = Gson()
    private val typeOfSparseArrayOfT = object : TypeToken<SparseArray<T>>() {

    }.type
    private val typeOfSparseArrayOfObject = object : TypeToken<SparseArray<Any>>() {

    }.type

    @Throws(IOException::class)
    override fun write(jsonWriter: JsonWriter, tSparseArray: SparseArray<T>?) {
        if (tSparseArray == null) {
            jsonWriter.nullValue()
            return
        }
        gson.toJson(gson.toJsonTree(tSparseArray, typeOfSparseArrayOfT), jsonWriter)
    }

    @Throws(IOException::class)
    override fun read(jsonReader: JsonReader): SparseArray<T>? {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull()
            return null
        }
        val temp = gson.fromJson<SparseArray<Any>>(jsonReader, typeOfSparseArrayOfObject)
        val result = SparseArray<T>(temp.size())
        var key: Int
        var tElement: JsonElement
        for (i in 0 until temp.size()) {
            key = temp.keyAt(i)
            tElement = gson.toJsonTree(temp.get(key))
            result.put(key, gson.fromJson(tElement, classOfT))
        }
        return result
    }

}