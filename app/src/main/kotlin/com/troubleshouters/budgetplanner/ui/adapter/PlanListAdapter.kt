package com.troubleshouters.budgetplanner.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.troubleshouters.budgetplanner.data.enums.PlanType
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.databinding.ViewPlanItemBinding

class PlanListAdapter(private var plans: List<Plan>) :
    RecyclerView.Adapter<PlanListAdapter.PlanViewHolder>() {

    private var onPlanEditClickListener: OnPlanEditClickListener? = null

    interface OnPlanEditClickListener {
        fun onPlanEditClick(plan: Plan)
    }

    fun setOnPlanEditClickListener(listener: OnPlanEditClickListener) {
        onPlanEditClickListener = listener
    }

    inner class PlanViewHolder(private val binding: ViewPlanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: Plan) {
            val totalBudget = when (plan.planType) {
                PlanType.DAILY -> plan.budget * 30
                PlanType.WEEKLY -> plan.budget * 4
                PlanType.MONTHLY -> plan.budget
            }

            binding.tvPlanTitle.text = plan.title
            binding.tvPlanType.text = plan.planType.name
            binding.tvPlanAmount.text = plan.budget.toString()
            binding.tvPlanMonthlyTotal.text = totalBudget.toString()

            binding.btnEdit.setOnClickListener {
                onPlanEditClickListener?.onPlanEditClick(plan)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val binding = ViewPlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        holder.bind(plans[position])
    }

    override fun getItemCount(): Int {
        return plans.size
    }

    fun submitList(plans: List<Plan>) {
        this.plans = plans
        this.notifyDataSetChanged()
    }

}
