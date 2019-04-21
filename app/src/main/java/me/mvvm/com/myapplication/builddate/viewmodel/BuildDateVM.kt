package me.mvvm.com.myapplication.builddate.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import me.mvvm.com.myapplication.data.models.BuildDate
import me.mvvm.com.myapplication.data.models.Response
import me.mvvm.com.myapplication.data.network.Api
import me.mvvm.com.myapplication.di.Injector
import me.mvvm.com.myapplication.ui.viewModels.base.BaseViewModel
import me.mvvm.com.myapplication.utils.Utils
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 4/20/19.
 * java.karpenko@gmail.com
 */
class BuildDateVM : BaseViewModel() {

    @Inject
    lateinit var api: Api

    private val key = "coding-puzzle-client-449cc9d"
    lateinit var buildList: LiveData<List<BuildDate>>
    var mutableData = MutableLiveData<List<BuildDate>>()

    init {
        Injector.msInjector?.plusBuildComponent()?.inject(this)
    }

    fun getListItems(): LiveData<List<BuildDate>> {
        return mutableData
    }


    fun initDataSoutce(item: String, item2 : Int) {
        compositeDisposable.add(
                api.getBuildDates(item2 ,item,  key)
                        .subscribe(
                                fun(response: Response) {
                                    mutableData.postValue(Utils.convertToListBuild(response.mapper))
                                },
                                {
                                }
                        )
        )
    }

}