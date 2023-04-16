package com.troubleshouters.budgetplanner.utils

import android.app.AlertDialog
import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showSnackbar(rootView: View, text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(rootView, text, duration).show()
}

fun showSuccessDialogWithAction(
    context: Context,
    message: String,
    actionText: String,
    action: () -> Unit
) {
    val builder = AlertDialog.Builder(context)
    builder.setMessage(message)
        .setPositiveButton(actionText) { dialog, id ->
            action.invoke()
        }
    val dialog = builder.create()
    dialog.show()
}
