package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var uid: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phoneNumber: String? = null
) : Parcelable {
    companion object {
        fun createFromFirebaseAuth(auth: FirebaseUser): User {
            return User(
                uid = auth.uid,
                name = auth.displayName,
                email = auth.email
            )
        }
    }
}