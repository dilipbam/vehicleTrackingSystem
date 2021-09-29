package com.dilip.deliverytruckbooking_dilipbam.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dilip.deliverytruckbooking_dilipbam.dao.OwnerDAO
import com.dilip.deliverytruckbooking_dilipbam.entity.Owner

@Database(
        entities = [(Owner::class)],
        version = 1,
        exportSchema = false
)
abstract class OwnerDB : RoomDatabase() {
    abstract fun getOwnerDAO(): OwnerDAO

    companion object {
        @Volatile
        private var instance: OwnerDB? = null

        fun getInstance(context: Context): OwnerDB {
            if (instance == null) {
                synchronized(OwnerDB::class) {
                    instance = buildDatabase(context)
                }
            }
            return instance!!
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        OwnerDB::class.java,
                        "OwnerDB"
                ).build()
    }
}