package com.bridor.app.data.repository

import com.bridor.app.data.database.BridorDatabase
import com.bridor.app.data.database.DailyStepsEntity
import com.bridor.app.domain.model.StepData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class StepsRepository(private val db: BridorDatabase) {

    private val dao = db.stepsDao()
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun getTodaySteps(): Flow<StepData> {
        val today = LocalDate.now().format(formatter)
        return dao.getStepsForDate(today).map { entity ->
            entity?.toDomain() ?: StepData(date = today)
        }
    }

    fun getRecentSteps(): Flow<List<StepData>> {
        return dao.getRecentSteps().map { list ->
            list.map { it.toDomain() }
        }
    }

    suspend fun updateSteps(steps: Int, distance: Float, calories: Float, activeMinutes: Int) {
        val today = LocalDate.now().format(formatter)
        val current = dao.getStepsForDate(today) // This is Flow, need different approach
        // For simplicity we upsert
        dao.upsert(
            DailyStepsEntity(
                date = today,
                steps = steps,
                distanceMeters = distance,
                calories = calories,
                activeMinutes = activeMinutes
            )
        )
    }

    suspend fun setGoal(goal: Int) {
        val today = LocalDate.now().format(formatter)
        dao.upsert(
            DailyStepsEntity(
                date = today,
                goal = goal
            )
        )
    }

    private fun DailyStepsEntity.toDomain() = StepData(
        date = date,
        steps = steps,
        distanceMeters = distanceMeters,
        calories = calories,
        activeMinutes = activeMinutes,
        goal = goal
    )
}
