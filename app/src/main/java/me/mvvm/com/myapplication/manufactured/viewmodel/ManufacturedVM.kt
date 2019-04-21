package me.mvvm.com.myapplication.manufactured.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import me.mvvm.com.myapplication.data.models.Manufactured
import me.mvvm.com.myapplication.data.network.Api
import me.mvvm.com.myapplication.di.Injector
import me.mvvm.com.myapplication.manufactured.view.adapter.ManufactoredDataSourceFactory
import me.mvvm.com.myapplication.manufactured.view.adapter.ManufacturedDataSource
import me.mvvm.com.myapplication.ui.viewModels.base.BaseViewModel
import me.mvvm.com.myapplication.utils.State
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class ManufacturedVM : BaseViewModel() {

    @Inject
    lateinit var api: Api

    lateinit var manufactoredList: LiveData<PagedList<Manufactured>>
    private lateinit var mManufacturedDataSource: ManufactoredDataSourceFactory
    var mutableData = MutableLiveData<Int>()

    init {
        Injector.msInjector?.plusManufacturedComponent()?.inject(this)
    }

    fun initDataSoutce(manufacturer: Int) {
        mManufacturedDataSource = ManufactoredDataSourceFactory(compositeDisposable, api, manufacturer)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize)
                .setEnablePlaceholders(true)
                .build()
        manufactoredList = LivePagedListBuilder<Int, Manufactured>(mManufacturedDataSource, config).build()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<ManufacturedDataSource,
            State>(mManufacturedDataSource.manufacturedSourceLiveData, ManufacturedDataSource::state)

    fun retry() {
        mManufacturedDataSource.manufacturedSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return manufactoredList.value?.isEmpty() ?: true
    }

    fun getSelectedItem(): LiveData<Int>{
        return mutableData
    }
}