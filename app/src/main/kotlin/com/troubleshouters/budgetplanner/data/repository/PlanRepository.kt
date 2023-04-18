package com.troubleshouters.budgetplanner.data.repository

import androidx.lifecycle.LiveData
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.data.local.plan.PlanDao
import com.troubleshouters.budgetplanner.data.local.transaction.Transaction
import com.troubleshouters.budgetplanner.data.local.transaction.TransactionDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlanRepository @Inject constructor(
    private val planDao: PlanDao,
    private val transactionDao: TransactionDao
) {

    fun getAllPlans(): LiveData<List<Plan>> {
        return planDao.getAllPlans()
    }

    fun getPlanById(planId: Long): LiveData<Plan?> {
        return planDao.getPlanById(planId)
    }

    fun getPlanBudgetForAMonth(): LiveData<Double?> {
        return planDao.getPlanBudgetForAMonth()
    }

    suspend fun insertPlan(plan: Plan) {
        planDao.insertPlan(plan)
    }

    suspend fun updatePlan(plan: Plan) {
        planDao.updatePlan(plan)
    }

    suspend fun deletePlan(plan: Plan) {
        planDao.deletePlan(plan)
    }

    suspend fun getAllTransactions(): List<Transaction> {
        return transactionDao.getAllTransactions()
    }

    suspend fun getAllTransactionsForPlan(planId: Long): List<Transaction> {
        return transactionDao.getAllTransactionsForPlan(planId)
    }

    suspend fun getTransactionById(planId: Long): Transaction? {
        return transactionDao.getTransactionById(planId)
    }

    suspend fun insertTransaction(transaction: Transaction) {
        transactionDao.insertTransaction(transaction)
    }

    suspend fun updateTransaction(transaction: Transaction) {
        transactionDao.updateTransaction(transaction)
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        transactionDao.deleteTransaction(transaction)
    }

}