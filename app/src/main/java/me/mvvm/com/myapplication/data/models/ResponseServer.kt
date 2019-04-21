package me.mvvm.com.myapplication.data.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

data class Response(

        @SerializedName("page") val page: Int,

        @SerializedName("pageSize") val pageSize:  Int,

        @SerializedName("totalPageCount") val totalPageCount:  Int,

        @SerializedName("wkda") val mapper: Map<String, String>

)
