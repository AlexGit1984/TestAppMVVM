package me.mvvm.com.myapplication.data.network

import io.reactivex.Flowable
import me.mvvm.com.myapplication.data.database.User
import retrofit2.http.GET


interface Api {
    @GET("get")
    fun getDataServer(): Flowable<User>
}