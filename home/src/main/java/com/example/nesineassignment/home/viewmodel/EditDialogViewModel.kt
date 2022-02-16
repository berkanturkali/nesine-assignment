package com.example.nesineassignment.home.viewmodel

import androidx.lifecycle.ViewModel

class EditDialogViewModel : ViewModel() {

    fun isValid(field: String?) = !field.isNullOrBlank()  && !field.isNullOrEmpty()
}