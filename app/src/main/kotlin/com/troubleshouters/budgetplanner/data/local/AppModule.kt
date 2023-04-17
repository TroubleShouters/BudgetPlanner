package com.troubleshouters.budgetplanner.data.local

import android.app.Application
import android.content.Context
import com.troubleshouters.budgetplanner.data.local.plan.PlanDao
import com.troubleshouters.budgetplanner.data.local.transaction.TransactionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun providePlanDao(database: BudgetPlannerDatabase): PlanDao {
        return database.planDao()
    }

    @Provides
    fun provideTransactionDao(database: BudgetPlannerDatabase): TransactionDao {
        return  database.transactionDao()
    }

    @Provides
    fun provideDatabase(context: Context): BudgetPlannerDatabase {
        return BudgetPlannerDatabase.getInstance(context)
    }

}