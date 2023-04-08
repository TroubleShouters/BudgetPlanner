package com.troubleshouters.budgetplanner.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(rootView: View, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(rootView, text, duration).show()
}