package me.mvvm.com.myapplication.ui.viewModels.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */

abstract class BaseViewModel : ViewModel() {

    protected val disposal : CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        if (!disposal.isDisposed) disposal.dispose()
    }
}