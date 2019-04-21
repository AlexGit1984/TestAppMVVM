package me.mvvm.com.myapplication.commonview.di

import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.commonview.viewmodel.FullCarsVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@Module
class FullCarsModule {

    @FirstPageScope
    @Provides
    internal fun provideViewModel(): FullCarsVM {
        return FullCarsVM()
    }
}