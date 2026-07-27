package com.bridor.app.domain.model

data class StepData(
    val date: String,           // yyyy-MM-dd
    val steps: Int = 0,
    val distanceMeters: Float = 0f,
    val calories: Float = 0f,
    val activeMinutes: Int = 0,
    val goal: Int = 10000
)

data class Shift(
    val id: String,
    val date: String,
    val startTime: String,      // HH:mm
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
    val tags: List<String> = emptyList(),
    val status: String = "scheduled"
)

data class Absence(
    val id: String,
    val date: String,
    val type: String,           // vacation, sick, holiday...
    val durationHours: Float = 0f,
    val status: String = "approved",
    val description: String? = null
)

data class KronosTag(
    val name: String,
    val description: String? = null,
    val date: String? = null,
    val color: String? = null
)
