package me.mvvm.com.myapplication.di

import dagger.Component
import me.mvvm.com.myapplication.mainscreen.di.MainComponent
import me.mvvm.com.myapplication.mainscreen.di.MainModule
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, RoomModule::class, NetworkApiModule::class))
interface AppComponent {

    fun plus(mainModule: MainModule): MainComponent
}



