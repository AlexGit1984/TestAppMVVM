package me.mvvm.com.myapplication.mainscreen.di

import dagger.Subcomponent
import me.mvvm.com.myapplication.mainscreen.viewmodel.MainActivityVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@MainPageScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivityVM: MainActivityVM)

}