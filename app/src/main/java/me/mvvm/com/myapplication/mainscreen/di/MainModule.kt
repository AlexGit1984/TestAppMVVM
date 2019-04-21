package me.mvvm.com.myapplication.mainscreen.di

import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.mainscreen.viewmodel.MainActivityVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@Module
class MainModule {

    @MainPageScope
    @Provides
    internal fun provideViewModel(): MainActivityVM {
        return MainActivityVM()
    }
}