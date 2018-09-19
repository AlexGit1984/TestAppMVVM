package me.mvvm.com.myapplication.ui.viewModels.base

import android.os.SystemClock
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */

abstract class BaseFragment : Fragment(){

    private var mAddToBAckStack: Boolean = false

    private var lastClickTime = SystemClock.elapsedRealtime()

    fun show(fragmentManager: FragmentManager) {
        show(fragmentManager, true)
    }

    fun show(fragmentManager: FragmentManager, addToBackStack: Boolean) {
        this.mAddToBAckStack = addToBackStack
        replaceFragment(fragmentManager, addToBackStack, getContainer())
    }

    private fun replaceFragment(fragmentManager: FragmentManager, addToBackStack: Boolean, container: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(container, this, getName())
        if (addToBackStack)
            transaction.addToBackStack(getName())
        transaction.commitAllowingStateLoss()
    }

    abstract fun getName(): String

    abstract fun getContainer(): Int

}