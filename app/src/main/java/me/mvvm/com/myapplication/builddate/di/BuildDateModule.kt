package me.mvvm.com.myapplication.commonview.di

import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.builddate.viewmodel.BuildDateVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@Module
class BuildDateModule {

    @ThirdPageScope
    @Provides
    internal fun provideViewModel(): BuildDateVM {
        return BuildDateVM()
    }
}