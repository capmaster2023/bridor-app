# Bridor

Application Android native professionnelle pour le suivi d'activité physique et la synchronisation des horaires Kronos.

## Fonctionnalités

- Compteur de pas en temps réel (STEP_COUNTER + Activity Recognition)
- Calcul distance / calories / temps actif
- Historique journalier, hebdomadaire et mensuel
- Objectif de pas configurable + reset automatique à minuit
- Synchronisation Kronos (URL personnalisée)
- Support JSON / ICS
- Punch In / Punch Out, pauses, absences, balises
- Calendrier professionnel (jour / semaine / mois)
- Notifications intelligentes
- Dashboard Material 3 moderne
- Stockage local sécurisé (Room + Android Keystore)
- WorkManager (sync arrière-plan + reset quotidien)
- GitHub Actions : génère APK Debug + Release

## Technologies

- Kotlin
- Jetpack Compose + Material 3
- Clean Architecture / MVVM
- Room Database
- Retrofit + OkHttp
- Kotlin Coroutines + Flow
- WorkManager
- Android Keystore
- minSdk 26 (Android 8.0)

## Structure

```
app/
├── data/
│   ├── api/
│   ├── database/
│   └── repository/
├── domain/
│   ├── model/
│   └── usecase/
├── presentation/
│   ├── dashboard/
│   ├── calendar/
│   ├── kronos/
│   └── settings/
├── security/
└── workers/
```

## Compilation

1. Ouvre le projet dans **Android Studio** (Hedgehog ou plus récent recommandé)
2. Sync Gradle
3. Run sur un appareil / émulateur

Ou en ligne de commande :

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## Configuration Kronos

Dans **Paramètres** → ajoute ton URL Kronos  
Exemple :  
`https://bridor.prd.mykronos.com/api/calendar_sync/...`

## Auteur

Projet généré pour capmaster2023
