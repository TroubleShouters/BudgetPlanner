package com.troubleshouters.budgetplanner.model

data class Plan(
    var type: PlanType,
    var title: String,
    var budget: Int
)
