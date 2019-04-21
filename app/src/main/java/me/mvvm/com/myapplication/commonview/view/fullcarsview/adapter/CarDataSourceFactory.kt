package me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import me.mvvm.com.myapplication.data.models.Car
import me.mvvm.com.myapplication.data.network.Api

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class CarDataSourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private val networkService: Api)
    : DataSource.Factory<Int, Car>() {

        val carSourceLiveData = MutableLiveData<CarDataSource>()

        override fun create(): DataSource<Int, Car> {
            val newsDataSource = CarDataSource(networkService, compositeDisposable)
            carSourceLiveData.postValue(newsDataSource)
            return newsDataSource
        }
    }
