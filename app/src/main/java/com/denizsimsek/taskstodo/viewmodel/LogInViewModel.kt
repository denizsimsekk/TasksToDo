package com.denizsimsek.taskstodo.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.denizsimsek.taskstodo.repository.LogInRepository
import com.denizsimsek.taskstodo.view.activities.HeadFeedActivity
import com.denizsimsek.taskstodo.view.activities.MemberFeedActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(private val context: Context, private val app: Application, private val repo: LogInRepository): BaseViewModel(app) {

    var signedIn= MutableLiveData<Boolean>()
    var userRole= MutableLiveData<String>()



    fun signIn(email :String, password :String)
    {

        launch {

            val job=async{signedIn.postValue( repo.login(email, password))}
            job.await()
            val job2=async {
                if(signedIn.value==true)
                {
                    println("signedIn true")
                    var job3=async {
                        userRole.postValue(repo.checkRole(email))
                    }
                    job3.await()



                }else
                {

                    println("signedIn false")
                }
            }
            job2.await()

        }

        println("runblocking")




    }
}
