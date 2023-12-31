package com.denizsimsek.taskstodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(application: Application) : AndroidViewModel(application),
    CoroutineScope {


    private val job= Job()

    //do the job then go back to main thread
    override val coroutineContext: CoroutineContext
        get() = job+ Dispatchers.Main
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }




}
