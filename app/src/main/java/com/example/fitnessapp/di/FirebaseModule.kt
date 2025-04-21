package com.example.fitnessapp.di

import com.example.fitnessapp.data.datasources.remote.FirebaseMusclesRemoteDataSource
import com.example.fitnessapp.data.datasources.repo_imp.MusclesRepoImp
import com.example.fitnessapp.domain.repo.MusclesRepository
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance("https://fitness-d7ae0-default-rtdb.firebaseio.com/")
    }

    @Provides
    @Singleton
    fun provideMusclesDatabaseReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference("Muscles")
    }

    @Provides
    @Singleton
    fun provideFirebaseMuscleRemoteDataSource(musclesRef: DatabaseReference): FirebaseMusclesRemoteDataSource {
        return FirebaseMusclesRemoteDataSource(musclesRef)
    }

    @Provides
    @Singleton
    fun provideMusclesRepository(firebaseMusclesRemoteDataSource: FirebaseMusclesRemoteDataSource): MusclesRepository {
        return MusclesRepoImp(firebaseMusclesRemoteDataSource)
    }

}