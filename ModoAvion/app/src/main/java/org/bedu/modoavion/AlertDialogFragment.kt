package org.bedu.modoavion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment


class AlertDialogFragment: DialogFragment() {

    private lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_dialog, container, false)
        btn = view.findViewById(R.id.btn)

        btn.setOnClickListener {
            dismiss()
        }

        return view
    }


}