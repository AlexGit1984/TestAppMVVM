package me.mvvm.com.myapplication.mainscreen.viewmodel

import me.mvvm.com.myapplication.ui.viewModels.base.BaseViewModel

/**
 * Created by Alexander Karpenko on 20.04.19
 * java.karpenko@gmail.com
 */

class MainActivityVM : BaseViewModel() {
//
//    @Inject
//    lateinit var api: Api
//
//    lateinit var car: Car
//
//    var buildList: LiveData<PagedList<Car>>
//    private val compositeDisposable = CompositeDisposable()
//    private val pageSize = 10
//    private val newsDataSourceFactory: CarDataSourceFactory
//
//    init {
//        Injector.msInjector?.plusMainActivity()?.inject(this)
//        newsDataSourceFactory = CarDataSourceFactory(compositeDisposable, api)
//        val config = PagedList.Config.Builder()
//                .setPageSize(pageSize)
//                .setInitialLoadSizeHint(pageSize * 2)
//                .setEnablePlaceholders(true)
//                .build()
//        buildList = LivePagedListBuilder<Int, Car>(newsDataSourceFactory, config).build()
//    }
//
//    fun getState(): LiveData<State> = Transformations.switchMap<CarDataSource,
//            State>(newsDataSourceFactory.buildSourceLiveData, CarDataSource::state)
//
//    fun retry() {
//        newsDataSourceFactory.buildSourceLiveData.value?.retry()
//    }
//
//    fun listIsEmpty(): Boolean {
//        return buildList.value?.isEmpty() ?: true
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.dispose()
//    }
}