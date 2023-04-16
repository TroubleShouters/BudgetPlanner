package com.troubleshouters.budgetplanner.ui.viewmodel

import androidx.lifecycle.*
import com.troubleshouters.budgetplanner.data.local.transaction.Transaction
import com.troubleshouters.budgetplanner.data.repository.PlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val planRepository: PlanRepository
) : ViewModel() {

    private val _transactionLiveData = MutableLiveData<Transaction?>()
    val transactionLiveData: LiveData<Transaction?>
        get() = _transactionLiveData

    fun getTransactionById(transactionId: Long) {
        viewModelScope.launch {
            val transaction = planRepository.getTransactionById(transactionId)
            _transactionLiveData.postValue(transaction)
        }
    }

    fun getAllTransactionsForPlan(planId: Long): LiveData<List<Transaction>> {
        return liveData {
            val transactions = planRepository.getAllTransactionsForPlan(planId)
            emit(transactions)
        }
    }

    fun insertTransaction(transaction: Transaction) {
        viewModelScope.launch {
            planRepository.insertTransaction(transaction)
        }
    }

    fun updateTransaction(transaction: Transaction) {
        viewModelScope.launch {
            planRepository.updateTransaction(transaction)
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            planRepository.deleteTransaction(transaction)
        }
    }
}
