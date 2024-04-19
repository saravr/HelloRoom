package com.example.helloroom

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Provides
    fun provideDocumentsDao(appDatabase: AppDatabase): DocumentsDao =
        appDatabase.documentsDao()

    @Provides
    fun provideSavedDao(appDatabase: AppDatabase): SavedDao =
        appDatabase.savedDao()
}
