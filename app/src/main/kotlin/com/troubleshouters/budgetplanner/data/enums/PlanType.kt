package com.troubleshouters.budgetplanner.data.enums

import com.troubleshouters.budgetplanner.R

enum class PlanType {
    DAILY, WEEKLY, MONTHLY;

    fun planTypeToViewId() = when (this) {
        DAILY -> R.id.chip_daily
        WEEKLY -> R.id.chip_weekly
        MONTHLY -> R.id.chip_monthly
    }

    fun viewIdToPlanType() = mapOf(
        R.id.chip_daily to DAILY,
        R.id.chip_weekly to WEEKLY,
        R.id.chip_monthly to MONTHLY
    )
}