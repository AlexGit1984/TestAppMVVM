package me.mvvm.com.myapplication.manufactured.view.adapter

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.disposables.CompositeDisposable
import me.mvvm.com.myapplication.data.models.Manufactured
import me.mvvm.com.myapplication.data.network.Api

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class ManufactoredDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val networkService: Api, private val manufactorId: Int)
    : DataSource.Factory<Int, Manufactured>() {

    val manufacturedSourceLiveData = MutableLiveData<ManufacturedDataSource>()

    override fun create(): DataSource<Int, Manufactured> {
        val newsDataSource = ManufacturedDataSource(networkService, compositeDisposable, manufactorId)
        manufacturedSourceLiveData.postValue(newsDataSource)
        return newsDataSource
    }
}
