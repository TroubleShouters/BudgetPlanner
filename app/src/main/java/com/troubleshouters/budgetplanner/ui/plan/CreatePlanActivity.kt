package com.troubleshouters.budgetplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.troubleshouters.budgetplanner.R
import com.troubleshouters.budgetplanner.databinding.ActivityCreatePlanBinding
import com.troubleshouters.budgetplanner.data.enums.PlanType
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.ui.viewmodel.PlanViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreatePlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlanBinding
    private lateinit var checkedPlanType: PlanType
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
        binding.apply {
            buttonSave.setOnClickListener {
                val title = etTitle.text.toString()
                val budgetText = etBudget.text.toString()
                val budget = budgetText.toDoubleOrNull()

                if (title.isNotEmpty() && budget != null) {
                    val plan = Plan(
                        planType = checkedPlanType,
                        title = title,
                        budget = budget
                    )
                    insertNewPlan(plan)
                } else {
                    TODO("Show error message")
                }
            }
        }
    }

    private fun insertNewPlan(plan: Plan) {
        try {
            viewModel.insertPlan(plan)
            Snackbar.make(
                binding.root,
                "Plan saved successfully",
                Snackbar.LENGTH_SHORT
            ).show()
            finish()
        } catch (e: Exception) {
            TODO("Handle exception")
        }
    }
}