package com.bridor.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        DailyStepsEntity::class,
        ShiftEntity::class,
        AbsenceEntity::class,
        SettingsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BridorDatabase : RoomDatabase() {
    abstract fun stepsDao(): StepsDao
    abstract fun shiftDao(): ShiftDao
    abstract fun absenceDao(): AbsenceDao
    abstract fun settingsDao(): SettingsDao

    companion object {
        @Volatile
        private var INSTANCE: BridorDatabase? = null

        fun getInstance(context: Context): BridorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BridorDatabase::class.java,
                    "bridor.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
