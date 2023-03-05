package com.katyandleo.lifty.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.katyandleo.lifty.R

class LiftDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.lift_dialog, null)
        dialog.setContentView(view)
        dialog.create()
        return dialog
    }
}