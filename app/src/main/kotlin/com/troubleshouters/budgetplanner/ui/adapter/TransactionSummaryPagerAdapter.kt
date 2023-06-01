package com.troubleshouters.budgetplanner.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.troubleshouters.budgetplanner.data.local.transaction.Transaction

class TransactionSummaryPagerAdapter(
    private val transactions: List<Transaction>
) : RecyclerView.Adapter<TransactionSummaryPagerAdapter.TransactionSummaryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionSummaryViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return transactions.count()
    }

    override fun onBindViewHolder(holder: TransactionSummaryViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    inner class TransactionSummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}