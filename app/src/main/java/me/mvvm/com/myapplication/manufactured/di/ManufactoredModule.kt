package me.mvvm.com.myapplication.mainscreen.di

import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.manufactured.di.SecondPageScope
import me.mvvm.com.myapplication.manufactured.viewmodel.ManufacturedVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@Module
class ManufactoredModule {

    @SecondPageScope
    @Provides
    internal fun provideViewModel(): ManufacturedVM {
        return ManufacturedVM()
    }
}