package me.mvvm.com.myapplication.commonview.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter.CarDataSource
import me.mvvm.com.myapplication.commonview.view.fullcarsview.adapter.CarDataSourceFactory
import me.mvvm.com.myapplication.data.models.Car
import me.mvvm.com.myapplication.data.network.Api
import me.mvvm.com.myapplication.di.Injector
import me.mvvm.com.myapplication.ui.viewModels.base.BaseViewModel
import me.mvvm.com.myapplication.utils.State
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class FullCarsVM : BaseViewModel() {

    @Inject
    lateinit var api: Api

    var carList: LiveData<PagedList<Car>>
    private val carsDataSourceFactory: CarDataSourceFactory
    var mutableData = MutableLiveData<Int>()

    init {
        Injector.msInjector?.plusCommonComponent()?.inject(this)
        carsDataSourceFactory = CarDataSourceFactory(compositeDisposable, api)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(true)
                .build()
        carList = LivePagedListBuilder<Int, Car>(carsDataSourceFactory, config).build()
    }

    fun getSelectedItem(): LiveData<Int>{
        return mutableData
    }

    fun getState(): LiveData<State> = Transformations.switchMap<CarDataSource,
            State>(carsDataSourceFactory.carSourceLiveData, CarDataSource::state)

    fun retry() {
        carsDataSourceFactory.carSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return carList.value?.isEmpty() ?: true
    }

}