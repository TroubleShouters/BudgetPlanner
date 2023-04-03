package com.troubleshouters.budgetplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.troubleshouters.budgetplanner.R
import com.troubleshouters.budgetplanner.databinding.ActivityCreatePlanBinding
import com.troubleshouters.budgetplanner.model.Plan
import com.troubleshouters.budgetplanner.model.PlanType

class CreatePlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlanBinding
    private lateinit var checkedPlanType: PlanType

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
            val plan = Plan(
                checkedPlanType,
                binding.etTitle.text.toString(),
                binding.etBudget.text.toString().toInt()
            )
            TODO("Save plan to database")
        }
    }
}