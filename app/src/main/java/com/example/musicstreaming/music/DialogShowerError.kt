package com.example.musicstreaming.music

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.musicstreaming.databinding.DialogFragmentBinding

class DialogShowerError(private val title: String, private val message: String, val drawable: Drawable): DialogFragment() {
    companion object {
        const val TAG = "DialogShowerError"
    }

    private lateinit var binding: DialogFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        if (dialog != null && dialog?.window != null) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dismissEmailConfirmation.setOnClickListener {
            dialog?.dismiss()
        }
        binding.dialogTitle.text = title
        binding.dialogMessage.text = message
        binding.dialogImage.setImageDrawable(drawable)
    }


}