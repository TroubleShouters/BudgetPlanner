package com.troubleshouters.budgetplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.troubleshouters.budgetplanner.R
import com.troubleshouters.budgetplanner.databinding.ActivityEditPlanBinding

class EditPlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditPlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPlanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}