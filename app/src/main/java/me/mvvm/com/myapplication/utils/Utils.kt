package me.mvvm.com.myapplication.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream

/**
 * Created by Alexander Karpenko on 13.09.18.
 * java.karpenko@gmail.com
 */
class Utils {

    companion object {
        @Throws(IOException::class)
        fun parseStream(stream: InputStream?): String {
            val allText = stream?.bufferedReader()?.use(BufferedReader::readText)
            val result = StringBuilder()
            result.append(allText)
            return result.toString()
        }
    }
}