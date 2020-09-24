package com.example.smartparking.data.model

data class User(
    val uid: String?,
    val name: String?,
    val email: String?
) {
    companion object {
        fun fromHashMap(map: Map<String, String>) : User {
            val name by map
            val email by map

            val data = User(null, name, email)
            return data
        }
    }
}