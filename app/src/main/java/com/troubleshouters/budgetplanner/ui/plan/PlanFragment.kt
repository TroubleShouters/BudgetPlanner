package com.troubleshouters.budgetplanner.ui.plan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.troubleshouters.budgetplanner.databinding.FragmentPlanBinding

class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPlanSummaryView()
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

}