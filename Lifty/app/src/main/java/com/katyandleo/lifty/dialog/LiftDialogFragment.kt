package com.katyandleo.lifty.dialog

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.util.DisplayMetrics
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.katyandleo.lifty.R
import com.katyandleo.lifty.data.Lift


class LiftDialogFragment(private val lift: Lift) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        val view = layoutInflater.inflate(R.layout.lift_dialog, null)
        customizeView(view, lift)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(dialog.window?.attributes)
        layoutParams.gravity = Gravity.TOP or Gravity.CENTER_HORIZONTAL
        dialog.window?.attributes = layoutParams
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(view)
        val window = dialog.window
        val params = window?.attributes
        params?.width = (context?.resources?.displayMetrics?.widthPixels?.times(0.9))!!.toInt()
        window?.attributes = params
        dialog.create()
        dialog.show()
        return dialog
    }

    internal fun customizeView(view: View, lift: Lift){
        view.findViewById<TextView>(R.id.sets_planned).text = context?.getString(R.string.sets_d) + " " + lift.sets
        view.findViewById<TextView>(R.id.reps_planned).text = context?.getString(R.string.reps) + " " + lift.reps
        view.findViewById<TextView>(R.id.weight_planned).text = context?.getString(R.string.weight) + " " + lift.weight + " lbs"
        view.findViewById<TextView>(R.id.editTextWeightActual).text = lift.weight.toString()
        view.findViewById<TextView>(R.id.editTextRPEActual).text = lift.rpe.toString()
        view.findViewById<TextView>(R.id.lift_title).text = lift.name
        view.findViewById<TextView>(R.id.notes_body).text = lift.notes
        view.findViewById<TextView>(R.id.RPE_planned).text = context?.getString(R.string.rpe_d) + " " + lift.reps.toString()
    }
}