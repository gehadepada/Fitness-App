package com.example.fitnessapp.di

import com.example.fitnessapp.data.datasources.firestore.repository.FirebaseRepoImp
import com.example.fitnessapp.domain.repo.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    fun provideFirestoreDatabase(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMusclesRepository(impl: FirebaseRepoImp): FirebaseRepository
}