package com.troubleshouters.budgetplanner.ui.viewmodel

import androidx.lifecycle.*
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.data.repository.PlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val planRepository: PlanRepository
) : ViewModel() {

    fun getAllPlans(): LiveData<List<Plan>> = planRepository.getAllPlans()

    fun getPlanById(planId: Long): LiveData<Plan?> = planRepository.getPlanById(planId)

    fun getPlanBudgetForAMonth(): LiveData<Double> = planRepository.getPlanBudgetForAMonth()

    fun insertPlan(plan: Plan) {
        viewModelScope.launch {
            planRepository.insertPlan(plan)
        }
    }

    fun updatePlan(plan: Plan) {
        viewModelScope.launch {
            planRepository.updatePlan(plan)
        }
    }

    fun deletePlan(plan: Plan) {
        viewModelScope.launch {
            planRepository.deletePlan(plan)
        }
    }

}