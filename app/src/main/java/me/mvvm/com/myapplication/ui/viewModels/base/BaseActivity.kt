package me.mvvm.com.myapplication.ui.viewModels.base

import android.support.annotation.AnimRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

abstract class BaseActivity : AppCompatActivity() {

    fun AppCompatActivity.replaceFragmentSafely(fragment: Fragment,
                                                tag: String,
                                                allowStateLoss: Boolean = false,
                                                @IdRes containerViewId: Int,
                                                @AnimRes enterAnimation: Int = 0,
                                                @AnimRes exitAnimation: Int = 0,
                                                @AnimRes popEnterAnimation: Int = 0,
                                                @AnimRes popExitAnimation: Int = 0) {
        val ft = supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                .replace(containerViewId, fragment, tag)
        if (!supportFragmentManager.isStateSaved) {
            ft.commit()
        } else if (allowStateLoss) {
            ft.commitAllowingStateLoss()
        }
    }
}