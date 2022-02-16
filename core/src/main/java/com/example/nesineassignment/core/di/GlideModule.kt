package com.example.nesineassignment.core.di

import android.content.Context
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
object GlideModule {

    @[Provides]
    fun provideCircularProgressDrawable(@ApplicationContext context: Context): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5F
        circularProgressDrawable.centerRadius = 30F
        circularProgressDrawable.start()
        return circularProgressDrawable
    }
}