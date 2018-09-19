package me.mvvm.com.myapplication.mainscreen.di

import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.mainscreen.viewmodel.MainActivityVM

/**
 * Created by Alexander Karpenko on 09.09.18.
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