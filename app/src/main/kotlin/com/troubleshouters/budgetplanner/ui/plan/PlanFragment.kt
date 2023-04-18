package com.troubleshouters.budgetplanner.ui.plan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.troubleshouters.budgetplanner.R
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.databinding.FragmentPlanBinding
import com.troubleshouters.budgetplanner.ui.adapter.PlanListAdapter
import com.troubleshouters.budgetplanner.ui.viewmodel.PlanViewModel
import com.troubleshouters.budgetplanner.utils.DividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!
    private val planViewModel: PlanViewModel by viewModels()
    private lateinit var planAdapter: PlanListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        val itemDecoration = DividerItemDecoration(requireContext(), R.drawable.divider_plan)

        binding.rvPlanList.layoutManager = layoutManager
        binding.rvPlanList.addItemDecoration(itemDecoration)

        planAdapter = PlanListAdapter(emptyList())
        planAdapter.setOnPlanEditClickListener(object : PlanListAdapter.OnPlanEditClickListener {
            override fun onPlanEditClick(plan: Plan) {
                val destination = PlanFragmentDirections.actionNavigationPlanFragmentToEditPlanActivity(plan)
                this@PlanFragment.findNavController().navigate(destination)
            }
        })

        binding.rvPlanList.adapter = planAdapter

        planViewModel.getAllPlans().observe(viewLifecycleOwner) {plans ->
            planAdapter.submitList(plans)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBudgetSum()
        setupPlanSummaryView()
    }

    private fun setupBudgetSum() {
        planViewModel.getPlanBudgetForAMonth().observe(viewLifecycleOwner) {budget ->
            val budgetText = budget?.toInt()?.toString() ?: "0"
            binding.viewPlanSummary.tvTotalBudget.text = budgetText
        }
    }

    private fun setupPlanSummaryView() {
        binding.viewPlanSummary.apply {
            btnSeeReport.setOnClickListener {view ->
                moveToReport(view)
            }

            btnCreatePlan.setOnClickListener {view ->
                moveToCreatePlan(view)
            }
        }
    }

    private fun moveToReport(view: View) {
        // TODO: Move to ReportActivity
    }

    private fun moveToCreatePlan(view: View) {
        val destination = PlanFragmentDirections.actionNavigationPlanFragmentToCreatePlanActivity()
        view.findNavController().navigate(destination)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}