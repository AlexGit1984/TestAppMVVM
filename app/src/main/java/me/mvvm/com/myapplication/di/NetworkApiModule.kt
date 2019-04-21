package me.mvvm.com.myapplication.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.BuildConfig
import me.mvvm.com.myapplication.data.network.Api
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
open class NetworkApiModule {

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Singleton
    @Provides
    fun provideRetrofit(httpUrl: HttpUrl,
                        client: OkHttpClient,
                        gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Singleton
    @Provides
    fun provideHttpUrl() = HttpUrl.parse(BuildConfig.API_BASE_URL)!!

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val timeDelay: Long = 40
        return OkHttpClient.Builder()
                .connectTimeout(timeDelay, TimeUnit.SECONDS)
                .readTimeout(timeDelay, TimeUnit.SECONDS)
                .writeTimeout(timeDelay, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .serializeNulls()
            .create()
}