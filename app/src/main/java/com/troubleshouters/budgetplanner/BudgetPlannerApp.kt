package com.troubleshouters.budgetplanner

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BudgetPlannerApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
    
}