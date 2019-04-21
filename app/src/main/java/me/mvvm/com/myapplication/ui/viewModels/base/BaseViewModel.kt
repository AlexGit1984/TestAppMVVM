package me.mvvm.com.myapplication.ui.viewModels.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected val pageSize = 15

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    }
}