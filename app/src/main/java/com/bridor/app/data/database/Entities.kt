package com.bridor.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_steps")
data class DailyStepsEntity(
    @PrimaryKey val date: String,
    val steps: Int = 0,
    val distanceMeters: Float = 0f,
    val calories: Float = 0f,
    val activeMinutes: Int = 0,
    val goal: Int = 10000
)

@Entity(tableName = "shifts")
data class ShiftEntity(
    @PrimaryKey val id: String,
    val date: String,
    val startTime: String,
    val endTime: String,
    val plannedStart: String? = null,
    val plannedEnd: String? = null,
    val punchIn: String? = null,
    val punchOut: String? = null,
    val breakStart: String? = null,
    val breakEnd: String? = null,
    val department: String? = null,
    val location: String? = null,
    val position: String? = null,
    val type: String? = null,
    val tagsJson: String? = null,
    val status: String = "scheduled"
)

@Entity(tableName = "absences")
data class AbsenceEntity(
    @PrimaryKey val id: String,
    val date: String,
    val type: String,
    val durationHours: Float = 0f,
    val status: String = "approved",
    val description: String? = null
)

@Entity(tableName = "settings")
data class SettingsEntity(
    @PrimaryKey val key: String,
    val value: String
)
