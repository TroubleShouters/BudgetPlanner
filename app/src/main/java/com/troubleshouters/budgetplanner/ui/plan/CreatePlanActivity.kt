package com.troubleshouters.budgetplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.troubleshouters.budgetplanner.R
import com.troubleshouters.budgetplanner.databinding.ActivityCreatePlanBinding
import com.troubleshouters.budgetplanner.data.enums.PlanType
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.ui.viewmodel.PlanViewModel
import com.troubleshouters.budgetplanner.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlanBinding
    private var checkedPlanType: PlanType = PlanType.DAILY
    private val viewModel: PlanViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupChipListener()
        setupButtonListener()
    }

    private fun setupChipListener() {
        binding.chipGroupType.setOnCheckedStateChangeListener { group, _ ->
            checkedPlanType = when (group.checkedChipId) {
                R.id.chip_daily -> PlanType.DAILY
                R.id.chip_weekly -> PlanType.WEEKLY
                R.id.chip_monthly -> PlanType.MONTHLY
                else -> PlanType.DAILY
            }
        }
    }

    private fun setupButtonListener() {
        binding.buttonSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val budgetText = binding.etBudget.text.toString()
            val budget = budgetText.toDoubleOrNull()

            if (title.isNotEmpty() && budget != null) {
                val plan = Plan(
                    planType = checkedPlanType,
                    title = title,
                    budget = budget
                )
                insertNewPlan(plan)
            } else {
                showSnackbar(binding.root, "Title or/and budget is empty")
            }
        }
    }

    private fun insertNewPlan(plan: Plan) {
        try {
            viewModel.insertPlan(plan)
            showSnackbar(binding.root, "Plan saved successfully")
            finish()
        } catch (e: Exception) {
            showSnackbar(binding.root, "Failed to save plan")
        }
    }
}