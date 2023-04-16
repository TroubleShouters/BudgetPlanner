package com.troubleshouters.budgetplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.navArgs
import com.troubleshouters.budgetplanner.data.enums.PlanType
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.databinding.ActivityEditPlanBinding
import com.troubleshouters.budgetplanner.ui.viewmodel.PlanViewModel
import com.troubleshouters.budgetplanner.utils.showConfirmationDialog
import com.troubleshouters.budgetplanner.utils.showSnackbar
import com.troubleshouters.budgetplanner.utils.showSuccessDialogWithAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditPlanBinding
    private val args: EditPlanActivityArgs by navArgs()
    private val planViewModel: PlanViewModel by viewModels()
    private var checkedPlanType = PlanType.DAILY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setupUpdateButton()
        setupDeleteButton()
    }

    private fun init() {
        val plan = args.plan
        val editable = Editable.Factory.getInstance()
        val titleEditable = editable.newEditable(plan.title)
        val budgetEditable = editable.newEditable(plan.budget.toString())

        checkedPlanType = plan.planType
        binding.chipGroupType.check(plan.planType.planTypeToViewId())
        binding.chipGroupType.isEnabled = false
        binding.etTitle.text = titleEditable
        binding.etBudget.text = budgetEditable
    }

    private fun setupUpdateButton() {
        binding.buttonUpdate.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val budgetText = binding.etBudget.text.toString()
            val budget = budgetText.toDoubleOrNull()

            if (title.isNotEmpty() && budget != null) {
                val plan = Plan(
                    planType = checkedPlanType,
                    title = title,
                    budget = budget
                )
                updatePlan(plan)
            } else {
                showSnackbar(binding.root, "Title or/and budget is empty")
            }
        }
    }

    private fun setupDeleteButton() {
        binding.buttonDelete.setOnClickListener {
            deletePlan(args.plan)
        }
    }

    private fun updatePlan(plan: Plan) {
        val closeActivity = { finish() }
        try {
            planViewModel.updatePlan(plan)
            showSuccessDialogWithAction(
                context = this,
                message = "Plan is updated successfully",
                action = closeActivity
            )
        } catch (e: Exception) {
            showSnackbar(binding.root, "Failed to update plan")
            Log.e("UpdatePlan", e.message.toString())
        }
    }

    private fun deletePlan(plan: Plan) {
        val closeActivity = { finish() }
        val confirmAction = {
            planViewModel.deletePlan(plan)
            showSuccessDialogWithAction(
                context = this,
                message = "Plan is deleted successfully",
                action = closeActivity
            )
        }

        try {
            showConfirmationDialog(
                context = this,
                message = "Are you sure want to delete this Plan?",
                confirmAction = confirmAction
            )

        } catch (e: Exception) {
            showSnackbar(binding.root, "Failed to delete plan")
            Log.e("DeletePlan", e.message.toString())
        }
    }
}