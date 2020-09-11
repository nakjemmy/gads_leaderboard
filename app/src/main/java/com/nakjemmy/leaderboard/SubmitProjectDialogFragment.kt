package com.nakjemmy.leaderboard

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.DialogFragment

class SubmitProjectDialogFragment: DialogFragment() {
    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.dialog_confirm, container, false)
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            window?.apply {
                setBackgroundDrawableResource(R.drawable.rounded_rect_20)
            }
        }

        rootView.findViewById<ImageButton>(R.id.imageButtonClose)?.setOnClickListener {
            listener.onDialogNegativeClick(this)
        }

        rootView.findViewById<Button>(R.id.buttonConfirm)?.setOnClickListener {
            listener.onDialogPositiveClick(this)
        }

        return rootView
    }
}