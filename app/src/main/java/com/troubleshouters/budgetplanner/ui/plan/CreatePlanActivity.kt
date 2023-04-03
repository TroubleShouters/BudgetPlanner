package com.troubleshouters.budgetplanner.ui.plan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.troubleshouters.budgetplanner.databinding.ActivityCreatePlanBinding

class CreatePlanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePlanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePlanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}