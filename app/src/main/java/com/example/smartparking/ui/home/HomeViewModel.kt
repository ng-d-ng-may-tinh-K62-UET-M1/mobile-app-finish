package com.example.smartparking.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel @ViewModelInject constructor(
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

}