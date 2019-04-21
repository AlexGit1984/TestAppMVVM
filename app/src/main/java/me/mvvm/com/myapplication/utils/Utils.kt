package me.mvvm.com.myapplication.utils

import me.mvvm.com.myapplication.data.models.BuildDate
import me.mvvm.com.myapplication.data.models.Car
import me.mvvm.com.myapplication.data.models.Manufactured
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream

/**
 * Created by Alexander Karpenko on 13.09.18.
 * java.karpenko@gmail.com
 */
class Utils {

    companion object {

        val key = "coding-puzzle-client-449cc9d"

        @Throws(IOException::class)
        fun parseStream(stream: InputStream?): String {
            val allText = stream?.bufferedReader()?.use(BufferedReader::readText)
            val result = StringBuilder()
            result.append(allText)
            return result.toString()
        }

        fun convertToListCars(mapCars: Map<String, String>): List<Car> {
            var list = mutableListOf<Car>()
            for ((k, v) in mapCars) {
                val car = Car(k, v)
                list.add(car)
            }
            return list
        }

        fun convertToListFabric(mapCars: Map<String, String>): List<Manufactured> {
            var list = mutableListOf<Manufactured>()
            for ((k, v) in mapCars) {
                val manufactured = Manufactured(k, v)
                list.add(manufactured)
            }
            return list
        }

        fun convertToListBuild(mapCars: Map<String, String>): List<BuildDate> {
            var list = mutableListOf<BuildDate>()
            for ((k, v) in mapCars) {
                val manufactured = BuildDate(k, v)
                list.add(manufactured)
            }
            return list
        }

    }
}