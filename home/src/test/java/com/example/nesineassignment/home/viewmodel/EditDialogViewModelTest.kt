package com.example.nesineassignment.home.viewmodel

import com.google.common.truth.Truth
import org.junit.Test

class EditDialogViewModelTest {

    private val viewModel = EditDialogViewModel()


    @Test
    fun `is valid returns false when field is empty`(){
        val given = " "
        val result = viewModel.isValid(given)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `is valid returns false when field is null`(){
        val given = null
        val result = viewModel.isValid(given)
        Truth.assertThat(result).isFalse()
    }

    @Test
    fun `is valid returns true if field is not empty or null`(){
        val given = "field"
        val result = viewModel.isValid(given)
        Truth.assertThat(result).isTrue()
    }
}