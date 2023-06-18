package com.denizsimsek.taskstodo.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LogInRepository @Inject constructor(private val auth: FirebaseAuth,
                                          private val fireStore: FirebaseFirestore
) {

    var signedIn: Boolean?=null
    var role:String?="0"


    suspend fun FirebaseAuth.signInWithEmailAndPasswordSuspend(email: String, password: String): FirebaseAuth = suspendCoroutine { continuation ->
        signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(this)
            } else {
                continuation.resumeWithException(task.exception ?: Exception("Authentication failed"))
            }
        }
    }

    suspend fun login(email: String, password: String) : Boolean? {

        try{
            val a= auth.signInWithEmailAndPassword(email, password).await()
            if(a.user?.email!=null)
            {

                signedIn=true
                println("true")
            }
        }catch (e:Exception)
        {
            println("exception: "+e.localizedMessage)

            signedIn=false
        }
        return signedIn

    }



    suspend fun checkRole(email: String): String?
    {

        val querySnapshot = fireStore.collection("Users")
            .whereEqualTo("userEmail", email)
            .get()
            .await()
        if (querySnapshot.isEmpty) {
            return null
        } else {
            val documentSnapshot = querySnapshot.documents.first()
            println("useRoleFromRepo: "+documentSnapshot.getString("userRole"))
            return documentSnapshot.getString("userRole")
        }



    }



}
