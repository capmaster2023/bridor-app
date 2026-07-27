package com.bridor.app.data.repository

import com.bridor.app.data.database.AbsenceEntity
import com.bridor.app.data.database.BridorDatabase
import com.bridor.app.data.database.SettingsEntity
import com.bridor.app.data.database.ShiftEntity
import com.bridor.app.domain.model.Absence
import com.bridor.app.domain.model.Shift
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.TimeUnit

class KronosRepository(private val db: BridorDatabase) {

    private val shiftDao = db.shiftDao()
    private val absenceDao = db.absenceDao()
    private val settingsDao = db.settingsDao()

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

    fun getUpcomingShifts(fromDate: String): Flow<List<Shift>> {
        return shiftDao.getUpcomingShifts(fromDate).map { list ->
            list.map { it.toDomain() }
        }
    }

    fun getAllShifts(): Flow<List<Shift>> {
        return shiftDao.getAllShifts().map { list -> list.map { it.toDomain() } }
    }

    fun getAbsences(): Flow<List<Absence>> {
        return absenceDao.getAll().map { list -> list.map { it.toDomain() } }
    }

    suspend fun getKronosUrl(): String? = settingsDao.get("kronos_url")

    suspend fun setKronosUrl(url: String) {
        settingsDao.set(SettingsEntity("kronos_url", url))
    }

    suspend fun sync() {
        val url = getKronosUrl() ?: return
        try {
            val request = Request.Builder().url(url).get().build()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return
                val body = response.body?.string() ?: return
                // Basic parsing – Kronos formats vary (JSON / ICS)
                // Placeholder: in production parse according to content-type
                parseAndStore(body)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun parseAndStore(raw: String) {
        // Simplified placeholder parser
        // Real implementation should detect JSON vs ICS and parse accordingly
        // For now we just keep existing data
    }

    private fun ShiftEntity.toDomain() = Shift(
        id = id,
        date = date,
        startTime = startTime,
        endTime = endTime,
        plannedStart = plannedStart,
        plannedEnd = plannedEnd,
        punchIn = punchIn,
        punchOut = punchOut,
        breakStart = breakStart,
        breakEnd = breakEnd,
        department = department,
        location = location,
        position = position,
        type = type,
        tags = tagsJson?.split(",")?.filter { it.isNotBlank() } ?: emptyList(),
        status = status
    )

    private fun AbsenceEntity.toDomain() = Absence(
        id = id,
        date = date,
        type = type,
        durationHours = durationHours,
        status = status,
        description = description
    )
}
