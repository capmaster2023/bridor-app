package com.bridor.app.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    var kronosUrl by remember { mutableStateOf("") }
    var stepGoal by remember { mutableStateOf("10000") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            "Paramètres",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("URL Kronos", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = kronosUrl,
            onValueChange = { kronosUrl = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("https://bridor.prd.mykronos.com/api/...") },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* save url */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enregistrer l'URL")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Objectif de pas", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = stepGoal,
            onValueChange = { stepGoal = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text("Autres options", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("• Notifications activées/désactivées", style = MaterialTheme.typography.bodyMedium)
        Text("• Mode sombre", style = MaterialTheme.typography.bodyMedium)
        Text("• Taille / Poids (pour calcul calories)", style = MaterialTheme.typography.bodyMedium)
        Text("• Fréquence de synchronisation", style = MaterialTheme.typography.bodyMedium)
        Text("• Export des données", style = MaterialTheme.typography.bodyMedium)
    }
}
