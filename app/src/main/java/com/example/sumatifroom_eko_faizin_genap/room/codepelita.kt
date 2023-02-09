package com.example.sumatifroom_eko_faizin_genap.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = [tb_barang::class], version = 1)
abstract class codepelita: RoomDatabase(){
    abstract fun brgDAO(): barangDAO
    companion object{
        @Volatile private var instance :codepelita? = null
        private var LOCK = Any()
        operator fun invoke (context: Context)= instance?:
        synchronized(LOCK) {
            instance?: builDatabase(context).also {
                instance = it
            }
        }
        private fun builDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            codepelita::class.java,
            "Barang,db"
        )
            .build()
    }
}