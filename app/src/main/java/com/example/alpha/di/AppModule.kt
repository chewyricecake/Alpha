package com.example.alpha.di

import com.example.alpha.Repository.ParkingRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFireStoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideRepositoryInstance(firebaseFirestore: FirebaseFirestore): ParkingRepository = ParkingRepository(firebaseFirestore)
}