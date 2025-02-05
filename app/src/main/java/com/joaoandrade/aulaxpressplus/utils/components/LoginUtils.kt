package com.joaoandrade.aulaxpressplus.utils.components

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.joaoandrade.aulaxpressplus.R

fun userHasUsername(context: Context, userId: String, onResult: (Boolean) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("users")
        .document(userId)
        .get()
        .addOnSuccessListener { document ->
            val username = document.getString("username")
            onResult(!username.isNullOrBlank())
        }
        .addOnFailureListener { exception ->
            Log.e(context.getString(R.string.firestore), context.getString(R.string.login_error_login_verify, exception.message))
            onResult(false)
        }
}