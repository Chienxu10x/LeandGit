package com.example.appcc

import com.example.appcc.model.InstalledApp

sealed class CreateIconEvent {
    class SelectRelatedApp(val installedApp: InstalledApp) : CreateIconEvent()
}