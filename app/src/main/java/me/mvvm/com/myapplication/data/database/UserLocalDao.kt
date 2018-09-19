package me.mvvm.com.myapplication.data.database

import android.arch.persistence.room.*
import io.reactivex.Flowable

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */

@Dao
interface UserLocalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(user: User?)

    @Update
    fun updateData( user: User?)

    @Delete
    fun deleteData(user: User?)

    @Query("SELECT * FROM user_table WHERE _id = :dataId")
    fun getData(dataId: Int?): Flowable<User>?

    @Query("SELECT * FROM user_table")
    fun getDataList(): Flowable<List<User>>?

}