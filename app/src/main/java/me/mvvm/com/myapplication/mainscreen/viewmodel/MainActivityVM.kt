package me.mvvm.com.myapplication.mainscreen.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import me.mvvm.com.myapplication.data.database.LocalDatabase
import me.mvvm.com.myapplication.data.database.User
import me.mvvm.com.myapplication.data.network.Api
import me.mvvm.com.myapplication.di.Injector
import me.mvvm.com.myapplication.ui.viewModels.base.BaseViewModel
import javax.inject.Inject

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */

class MainActivityVM : BaseViewModel() {

    private var answer: MutableLiveData<User>? = null

    @Inject
    lateinit var db: LocalDatabase

    @Inject
    lateinit var api: Api

    lateinit var user: User

    init {
        Injector.msInjector?.plusMainActivity()?.inject(this)
    }


    fun getResponse(): LiveData<User>? {
        if (answer == null) {
            answer = MutableLiveData()
            getUser()
            disposal.add(db.localUserDao().getData(1)?.subscribe({
                it: User ->
                answer?.postValue(it) }))
        }
        return answer
    }


    fun getUser() {
     api.getDataServer().subscribe({idt: User? ->
         Log.d("MVVM", "response = " +idt?.name)
         user=idt!!
         db.localUserDao().insertData(idt)

     })

    }

    fun changeName(s: String) {
  Thread(Runnable {
      db.localUserDao().updateData(user.also {
          it.id=1
          it.name=s })
  }).start()

    }

}