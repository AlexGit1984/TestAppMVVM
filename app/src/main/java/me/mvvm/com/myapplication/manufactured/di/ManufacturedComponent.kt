package me.mvvm.com.myapplication.manufactured.di

import dagger.Subcomponent
import me.mvvm.com.myapplication.mainscreen.di.ManufactoredModule
import me.mvvm.com.myapplication.manufactured.viewmodel.ManufacturedVM

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@SecondPageScope
@Subcomponent(modules = arrayOf(ManufactoredModule::class))
interface ManufacturedComponent {
    fun inject(manufacturedVM: ManufacturedVM)

}