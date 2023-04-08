package com.troubleshouters.budgetplanner.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.troubleshouters.budgetplanner.data.repository.PlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanViewModel @Inject constructor(
    private val planRepository: PlanRepository
) : ViewModel() {

}