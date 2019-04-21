package me.mvvm.com.myapplication.di

import dagger.Component
import me.mvvm.com.myapplication.builddate.di.BuildDateComponent
import me.mvvm.com.myapplication.commonview.di.BuildDateModule
import me.mvvm.com.myapplication.commonview.di.CommonComponent
import me.mvvm.com.myapplication.commonview.di.FullCarsModule
import me.mvvm.com.myapplication.mainscreen.di.MainComponent
import me.mvvm.com.myapplication.mainscreen.di.MainModule
import me.mvvm.com.myapplication.mainscreen.di.ManufactoredModule
import me.mvvm.com.myapplication.manufactured.di.ManufacturedComponent
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkApiModule::class))
interface AppComponent {

    fun plus(mainModule: MainModule): MainComponent

    fun plus(mainModule: FullCarsModule): CommonComponent

    fun plus(mainModule: ManufactoredModule): ManufacturedComponent

    fun plus(buildDateModule: BuildDateModule): BuildDateComponent
}



