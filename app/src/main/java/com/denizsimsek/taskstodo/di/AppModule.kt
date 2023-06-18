package com.denizsimsek.taskstodo.di

import android.app.Application
import android.content.Context
import com.denizsimsek.taskstodo.repository.LogInRepository
import com.denizsimsek.taskstodo.viewmodel.LogInViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule  {
    @Singleton
    @Provides
    @Named("Auth")
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()


    @Singleton
    @Provides
    @Named("FireStore")
    fun proivdeFireStore(): FirebaseFirestore
    {
        return Firebase.firestore
    }

    @Singleton
    @Provides
    @Named("LogInRepository")
    fun providesLogInRepository(@Named("Auth") auth: FirebaseAuth, @Named("FireStore") fireStore: FirebaseFirestore):LogInRepository
    {
        return LogInRepository(auth,fireStore)
    }//: LogInRepository = LogInRepository(auth)

    @Singleton
    @Provides
    @Named("Application")
    fun provideApplication(): Application
    {
        return Application()
    }


    @Singleton
    @Provides
    fun provideslogInViewmModel(@ApplicationContext appContext: Context, @Named("Application") app: Application, @Named("LogInRepository") repo:LogInRepository):LogInViewModel {

        return LogInViewModel(appContext,app,repo)
    }



}
