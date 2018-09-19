package me.mvvm.com.myapplication.data.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by Alexander Karpenko on 09.09.18.
 * java.karpenko@gmail.com
 */

@Entity(tableName = "user_table")
 data class User (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id : Int,
    @ColumnInfo(name = "user_name")
    @SerializedName("user_name")
    var name : String
)