package com.troubleshouters.budgetplanner.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.data.local.plan.PlanDao
import com.troubleshouters.budgetplanner.data.local.transaction.Transaction
import com.troubleshouters.budgetplanner.data.local.transaction.TransactionDao

@Database(entities = [Plan::class, Transaction::class], version = 1)
abstract class BudgetPlannerDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: BudgetPlannerDatabase? = null

        fun getInstance(context: Context): BudgetPlannerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetPlannerDatabase::class.java,
                    "budget_planner_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
