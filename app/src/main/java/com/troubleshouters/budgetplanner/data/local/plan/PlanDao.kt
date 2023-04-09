package com.troubleshouters.budgetplanner.data.local.plan

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlanDao {
    @Query("SELECT * FROM plans")
    suspend fun getAllPlans(): List<Plan>

    @Query("SELECT * FROM plans WHERE id = :id")
    suspend fun getPlanById(id: Long): Plan?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: Plan)

    @Update
    suspend fun updatePlan(plan: Plan)

    @Delete
    suspend fun deletePlan(plan: Plan)

    @Query("SELECT SUM(CASE " +
            "WHEN plan_type = 'DAILY' THEN budget * 30 " +
            "WHEN plan_type = 'WEEKLY' THEN budget * 4 " +
            "ELSE budget END) FROM plans")
    fun getPlanBudgetForAMonth(): LiveData<Double>

}
