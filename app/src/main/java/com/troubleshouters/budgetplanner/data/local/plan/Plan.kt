package com.troubleshouters.budgetplanner.data.local.plan

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.troubleshouters.budgetplanner.data.enums.PlanType
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "plans")
data class Plan (
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "plan_type") var planType: PlanType = PlanType.DAILY,
    var title: String,
    var budget: Int
): Parcelable