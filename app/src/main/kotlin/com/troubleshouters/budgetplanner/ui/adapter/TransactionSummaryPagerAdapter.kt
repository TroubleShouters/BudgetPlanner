package com.troubleshouters.budgetplanner.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.troubleshouters.budgetplanner.data.local.plan.Plan
import com.troubleshouters.budgetplanner.databinding.ViewTransactionSummaryBinding

class TransactionSummaryPagerAdapter(
    private val plans: List<Plan>
) : RecyclerView.Adapter<TransactionSummaryPagerAdapter.TransactionSummaryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionSummaryViewHolder {
        val binding = ViewTransactionSummaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionSummaryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return plans.count()
    }

    override fun onBindViewHolder(
        holder: TransactionSummaryViewHolder,
        position: Int
    ) {
        holder.bind(plans[position])
    }

    inner class TransactionSummaryViewHolder(
        private val binding: ViewTransactionSummaryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(plan: Plan) {
            binding.apply {
                tvPlanType.text = plan.planType.toString()
                // tvDate.text
                tvPlanTitle.text = plan.title
                // tvPlanAmount.text

            }
        }
    }

}