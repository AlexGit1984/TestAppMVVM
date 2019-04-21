package me.mvvm.com.myapplication.mainscreen.view

import android.os.Bundle
import me.mvvm.com.myapplication.R
import me.mvvm.com.myapplication.commonview.view.fullcarsview.FullCarsFragment
import me.mvvm.com.myapplication.ui.viewModels.base.BaseActivity
import me.mvvm.com.myapplication.utils.OnBackPressed


/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */
class MainScreen : BaseActivity() {

    val FIRST_FRAGMENT = "FIRST_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(me.mvvm.com.myapplication.R.layout.activity_main)
        if (savedInstanceState == null) {
            replaceFragment()
        }
    }

    override fun onBackPressed() {
        val fragment =
                this.supportFragmentManager.findFragmentById(R.id.container)
        (fragment as? OnBackPressed)?.onBackPressed()?.not()?.let {
            super.onBackPressed()
        }
    }

    private fun replaceFragment() {
        replaceFragmentSafely(
                fragment = FullCarsFragment(),
                tag = FIRST_FRAGMENT,
                enterAnimation= R.anim.abc_popup_enter,
                containerViewId = R.id.container,
                allowStateLoss = true
        )
    }
}


