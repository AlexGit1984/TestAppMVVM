package me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers
import me.mvvm.com.myapplication.data.models.Car
import me.mvvm.com.myapplication.data.models.Response
import me.mvvm.com.myapplication.data.network.Api
import me.mvvm.com.myapplication.utils.State
import me.mvvm.com.myapplication.utils.Utils

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class CarDataSource(

        private val networkService: Api,
        private val compositeDisposable: CompositeDisposable)
    : PageKeyedDataSource<Int, Car>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null
    private val key = "coding-puzzle-client-449cc9d"


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Car>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getAllCars(0, params.requestedLoadSize, key)
                        .subscribe(
                                fun(response: Response) {
                                    updateState(State.DONE)

                                    callback.onResult(Utils.convertToListCars(response.mapper),
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

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
        updateState(State.LOADING)
        compositeDisposable.add(
                networkService.getAllCars(params.key, params.requestedLoadSize, key)
                        .subscribe(
                                fun(response: Response) {
                                    updateState(State.DONE)
                                    callback.onResult(Utils.convertToListCars(response.mapper),
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

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Car>) {
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

