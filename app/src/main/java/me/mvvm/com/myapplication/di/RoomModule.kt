package me.mvvm.com.myapplication.di

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import me.mvvm.com.myapplication.AndroidApplication
import me.mvvm.com.myapplication.data.database.LocalDatabase
import me.mvvm.com.myapplication.data.database.UserLocalDao
import javax.inject.Singleton

/**
 * Created by Alexander Karpenko on 14.09.18.
 * java.karpenko@gmail.com
 */
@Module
class RoomModule (application: AndroidApplication?,
                  val localDatabase: LocalDatabase = Room.databaseBuilder(application!!.applicationContext, LocalDatabase::class.java, "local-db").build()) {


    @Singleton
    @Provides
    internal fun providesRoomDatabase(): LocalDatabase {
        return localDatabase
    }

    @Singleton
    @Provides
    internal fun providesDbDao(localDatabase: LocalDatabase): UserLocalDao {
        return localDatabase.localUserDao()
    }

}