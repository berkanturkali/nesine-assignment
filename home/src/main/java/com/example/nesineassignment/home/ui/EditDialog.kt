package com.example.nesineassignment.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.nesineassignment.common.Constants.BODY_KEY
import com.example.nesineassignment.common.Constants.TITLE_KEY
import com.example.nesineassignment.common.setNavigationResult
import com.example.nesineassignment.home.databinding.DialogEditBinding
import com.example.nesineassignment.home.viewmodel.EditDialogViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class EditDialog : BottomSheetDialogFragment() {

    private var _binding: DialogEditBinding?  =null
    private val binding get() = _binding!!

    private val args by navArgs<EditDialogArgs>()

    private val viewModel by viewModels<EditDialogViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = DialogEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editEt.setText(args.titleDesc)
        binding.toolbar.title = if (args.isTitle) "Update Title" else "Update Body"
        binding.updateBtn.setOnClickListener {
            val field = binding.editEt.text?.toString()?.trim()
            if (viewModel.isValid(field)) {
                val key = if (args.isTitle) TITLE_KEY else BODY_KEY
                setNavigationResult(key, field)
                dismiss()
            } else {
                Snackbar.make(this.requireView(), "Field can not be empty", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(ContextCompat.getColor(this.requireContext(),
                        com.example.nesineassignment.common.R.color.error))
                    .show()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}