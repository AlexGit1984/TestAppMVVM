package me.mvvm.com.myapplication.di

import me.mvvm.com.myapplication.AndroidApplication
import me.mvvm.com.myapplication.builddate.di.BuildDateComponent
import me.mvvm.com.myapplication.commonview.di.BuildDateModule
import me.mvvm.com.myapplication.commonview.di.CommonComponent
import me.mvvm.com.myapplication.commonview.di.FullCarsModule
import me.mvvm.com.myapplication.mainscreen.di.MainComponent
import me.mvvm.com.myapplication.mainscreen.di.ManufactoredModule
import me.mvvm.com.myapplication.manufactured.di.ManufacturedComponent

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

open class Injector {


    lateinit var component: AppComponent

    private var mMainComponent: MainComponent? = null
   private var mCommonComponent: CommonComponent? = null
   private var mManufacturedComponent: ManufacturedComponent? = null
    private var mBuildComponent: BuildDateComponent? = null

    companion object {

        var msInjector: Injector? = Injector()
        @JvmStatic
        fun init(app: AndroidApplication?) {
            msInjector?.component = DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .networkApiModule(NetworkApiModule())
                    .build()
        }

    }


    fun plusCommonComponent(): CommonComponent {
        if (mCommonComponent == null) {
            mCommonComponent = component.plus(FullCarsModule())
        }
        return mCommonComponent as CommonComponent
    }

    fun plusManufacturedComponent(): ManufacturedComponent {
        if (mManufacturedComponent == null) {
            mManufacturedComponent = component.plus(ManufactoredModule())
        }
        return mManufacturedComponent as ManufacturedComponent
    }

    fun plusBuildComponent(): BuildDateComponent {
        if (mBuildComponent == null) {
            mBuildComponent = component.plus(BuildDateModule())
        }
        return mBuildComponent as BuildDateComponent
    }


}