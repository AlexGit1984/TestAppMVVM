package me.mvvm.com.myapplication.builddate.di

import dagger.Subcomponent
import me.mvvm.com.myapplication.builddate.viewmodel.BuildDateVM
import me.mvvm.com.myapplication.commonview.di.BuildDateModule
import me.mvvm.com.myapplication.commonview.di.FirstPageScope

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
@FirstPageScope
@Subcomponent(modules = arrayOf(BuildDateModule::class))
interface BuildDateComponent {
    fun inject(buildDateVM: BuildDateVM)

}