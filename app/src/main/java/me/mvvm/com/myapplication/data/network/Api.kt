package me.mvvm.com.myapplication.data.network

import io.reactivex.Flowable
import me.mvvm.com.myapplication.data.models.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("manufacturer")
    fun getAllCars(@Query("page") page: Int, @Query("pageSize") pageSize: Int, @Query("wa_key") key: String): Flowable<Response>

    @GET("main-types")
    fun getManufactures(@Query("page") page: Int, @Query("pageSize") pageSize: Int, @Query("manufacturer") manufacturer: Int, @Query("wa_key") key: String): Flowable<Response>


    @GET("built-dates")
    fun getBuildDates( @Query("manufacturer") manufacturer: Int,
                       @Query("main-type")    maintype: String,
                       @Query("wa_key") key: String): Flowable<Response>


}