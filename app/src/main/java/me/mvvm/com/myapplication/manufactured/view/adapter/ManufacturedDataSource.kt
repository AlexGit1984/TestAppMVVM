package me.mvvm.com.myapplication.manufactured.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import me.mvvm.com.myapplication.data.models.Manufactured
import me.mvvm.com.myapplication.data.models.Response
import me.mvvm.com.myapplication.data.network.Api
import me.mvvm.com.myapplication.utils.State
import me.mvvm.com.myapplication.utils.Utils
import me.mvvm.com.myapplication.utils.Utils.Companion.key

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class ManufacturedDataSource(private val networkService: Api,
                             private val compositeDisposable: CompositeDisposable, private val manufacturer: Int)
    : PageKeyedDataSource<Int, Manufactured>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null


    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Int>, callback: PageKeyedDataSource.LoadInitialCallback<Int, Manufactured>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getManufactures(0, params.requestedLoadSize, manufacturer, key)
                        .subscribe(
                                fun(response: Response) {
                                    updateState(State.DONE)
                                    callback.onResult(Utils.convertToListFabric(response.mapper),
                                            null,
                                            1
                                    )
                                },
                                {
                                    updateState(State.ERROR)
                                    setRetry(Action { loadInitial(params, callback) })
                                }
                        )
        )
    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Manufactured>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getManufactures(params.key, params.requestedLoadSize, manufacturer, key)
                        .subscribe(
                                fun(response: Response) {
                                    updateState(State.DONE)
                                    callback.onResult(Utils.convertToListFabric(response.mapper),
                                            params.key + 1
                                    )
                                },
                                {
                                    updateState(State.ERROR)
                                    setRetry(Action { loadAfter(params, callback) })
                                }
                        )
        )
    }

    override fun loadBefore(params: PageKeyedDataSource.LoadParams<Int>, callback: PageKeyedDataSource.LoadCallback<Int, Manufactured>) {
    }

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}