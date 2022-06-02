package com.example.musicstreaming.music

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.commit
import com.example.musicstreaming.R
import com.example.musicstreaming.databinding.DialogFragmentBinding
import com.example.musicstreaming.music.authentification.WelcomeFragment
import com.example.musicstreaming.music.authentification.login.LoginFragment
import com.example.musicstreaming.music.authentification.register.RegisterFragment

class DialogShowerRegister : DialogFragment() {

    companion object {
        const val TAG = "DialogShower"
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
            redirectToLoginFragment()
        }
        binding.dialogTitle
    }

    private fun redirectToLoginFragment() {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.main_fragment_container, WelcomeFragment())
            remove(RegisterFragment())
        }
    }
}