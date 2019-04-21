package me.mvvm.com.myapplication.ui.viewModels.base

import android.os.Bundle
import android.support.annotation.AnimRes
import android.support.annotation.IdRes
import android.support.v4.app.Fragment


/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

abstract class BaseFragment : Fragment(){


    fun Fragment.replaceFragmentSafely(fragment: Fragment,
                                       tag: String,
                                       allowStateLoss: Boolean = false,
                                       bundle: Bundle,
                                       @IdRes containerViewId: Int,
                                       @AnimRes enterAnimation: Int = 0,
                                       @AnimRes exitAnimation: Int = 0,
                                       @AnimRes popEnterAnimation: Int = 0,
                                       @AnimRes popExitAnimation: Int = 0) {
        fragment.arguments = bundle
        val ft = fragmentManager?.beginTransaction()?.setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)?.replace(containerViewId, fragment)?.addToBackStack(tag)
        if (!fragmentManager?.isStateSaved!!) {
            ft?.commit()
        } else if (allowStateLoss) {
            ft?.commitAllowingStateLoss()
        }
    }

}