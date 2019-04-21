package me.mvvm.com.myapplication.commonview.di

import dagger.Subcomponent
import me.mvvm.com.myapplication.commonview.viewmodel.FullCarsVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@FirstPageScope
@Subcomponent(modules = arrayOf(FullCarsModule::class))
interface CommonComponent {
    fun inject(fullCars: FullCarsVM)

}