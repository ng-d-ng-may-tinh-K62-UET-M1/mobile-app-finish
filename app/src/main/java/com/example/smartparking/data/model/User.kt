package com.example.smartparking.data.model

import android.os.Parcelable
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.PropertyName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @get:PropertyName("uid")
    @set:PropertyName("uid")
    var uid: String? = null,

    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String? = null,

    @get:PropertyName("email")
    @set:PropertyName("email")
    var email: String? = null,

    @get:PropertyName("phone_number")
    @set:PropertyName("phone_number")
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

        const val UID_FIELD = "uid"
        const val NAME_FIELD = "name"
        const val EMAIL_FIELD = "email"
        const val PHONE_FIELD = "phone_number"
    }
}