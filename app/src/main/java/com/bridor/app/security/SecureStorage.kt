package com.bridor.app.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

/**
 * Stockage sécurisé utilisant Android Keystore via EncryptedSharedPreferences.
 * À utiliser pour l'URL Kronos et toute donnée sensible.
 */
class SecureStorage(context: Context) {

    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "bridor_secure_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveKronosUrl(url: String) {
        prefs.edit().putString("kronos_url", url).apply()
    }

    fun getKronosUrl(): String? = prefs.getString("kronos_url", null)

    fun clear() {
        prefs.edit().clear().apply()
    }
}
