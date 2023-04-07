package com.troubleshouters.budgetplanner.data.local.plan

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
}
