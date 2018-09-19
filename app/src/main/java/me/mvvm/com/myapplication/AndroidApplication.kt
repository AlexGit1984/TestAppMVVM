package me.mvvm.com.myapplication

import android.app.Application
import com.facebook.stetho.Stetho
import me.mvvm.com.myapplication.di.Injector.Companion.init
import timber.log.Timber

/**
 * Created by Alexander Karpenko on 08.09.18.
 * java.karpenko@gmail.com
 */
class AndroidApplication: Application() {

    companion object {
        //platformStatic allow access it from java code
    }

    override fun onCreate() {
        super.onCreate()
        init(this)

        if(BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) +"::Line:" + element.lineNumber +"::" + element.methodName + "()"
                }
            })
        }
    }
}