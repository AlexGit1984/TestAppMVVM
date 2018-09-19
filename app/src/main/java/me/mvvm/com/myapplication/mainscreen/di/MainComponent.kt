package me.mvvm.com.myapplication.mainscreen.di

import dagger.Subcomponent
import me.mvvm.com.myapplication.mainscreen.viewmodel.MainActivityVM

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */
@MainPageScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivityVM: MainActivityVM)

}