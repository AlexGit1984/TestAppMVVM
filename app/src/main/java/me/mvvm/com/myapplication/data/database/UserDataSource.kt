package me.mvvm.com.myapplication.data.database

import io.reactivex.Flowable


/**
 * Created by Alexander Karpenko on 14.09.18.
 * java.karpenko@gmail.com
 */
class UserDataSource (var localDao: UserLocalDao?) : UserLocalDao {

    override fun deleteData(user: User?) {
        localDao?.deleteData(user)
    }

    override fun updateData(user: User?) {
        localDao?.updateData(user)
    }

    override fun getData(dataId: Int?): Flowable<User>? {
        return localDao?.getData(dataId)
    }

    override fun getDataList(): Flowable<List<User>>? {
        return localDao?.getDataList()
    }

    override fun insertData(user: User?) {
        localDao?.insertData(user)
    }

}