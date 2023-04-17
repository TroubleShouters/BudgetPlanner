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
    actionText: String = "OK",
    action: () -> Unit
) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton(actionText) { _, _ -> action.invoke() }
        .create()
        .show()
}

fun showConfirmationDialog(
    context: Context,
    message: String,
    confirmText: String = "OK",
    cancelText: String = "Cancel",
    confirmAction: () -> Unit,
    cancelAction: () -> Unit = {}
) {
    AlertDialog.Builder(context)
        .setMessage(message)
        .setPositiveButton(confirmText) { _, _ -> confirmAction.invoke() }
        .setNegativeButton(cancelText) { _, _ -> cancelAction.invoke() }
        .create()
        .show()
}
