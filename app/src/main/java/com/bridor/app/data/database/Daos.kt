package com.bridor.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StepsDao {
    @Query("SELECT * FROM daily_steps WHERE date = :date LIMIT 1")
    fun getStepsForDate(date: String): Flow<DailyStepsEntity?>

    @Query("SELECT * FROM daily_steps ORDER BY date DESC LIMIT 31")
    fun getRecentSteps(): Flow<List<DailyStepsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(steps: DailyStepsEntity)

    @Query("UPDATE daily_steps SET steps = :steps, distanceMeters = :distance, calories = :calories, activeMinutes = :active WHERE date = :date")
    suspend fun updateSteps(date: String, steps: Int, distance: Float, calories: Float, active: Int)
}

@Dao
interface ShiftDao {
    @Query("SELECT * FROM shifts WHERE date = :date ORDER BY startTime ASC")
    fun getShiftsForDate(date: String): Flow<List<ShiftEntity>>

    @Query("SELECT * FROM shifts WHERE date >= :fromDate ORDER BY date ASC, startTime ASC")
    fun getUpcomingShifts(fromDate: String): Flow<List<ShiftEntity>>

    @Query("SELECT * FROM shifts ORDER BY date DESC, startTime DESC LIMIT 50")
    fun getAllShifts(): Flow<List<ShiftEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(shifts: List<ShiftEntity>)

    @Query("DELETE FROM shifts")
    suspend fun clearAll()
}

@Dao
interface AbsenceDao {
    @Query("SELECT * FROM absences ORDER BY date DESC")
    fun getAll(): Flow<List<AbsenceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(absences: List<AbsenceEntity>)
}

@Dao
interface SettingsDao {
    @Query("SELECT value FROM settings WHERE key = :key LIMIT 1")
    suspend fun get(key: String): String?

    @Query("SELECT value FROM settings WHERE key = :key LIMIT 1")
    fun getFlow(key: String): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun set(setting: SettingsEntity)
}
