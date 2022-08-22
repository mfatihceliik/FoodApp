package com.muhammedfatihcelik.foodorder.di

import android.content.Context
import android.content.SharedPreferences
import com.muhammedfatihcelik.foodorder.data.local.LocalDataSource
import com.muhammedfatihcelik.foodorder.data.local.SharedPreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class DatabaseModule {

    @Provides
    fun sharedPreferenceManager(@ApplicationContext context: Context) : SharedPreferenceManager {
        return SharedPreferenceManager(context = context)
    }

    @Provides
    fun localDataSource(sharedPreferencesManager: SharedPreferenceManager) : LocalDataSource {
        return LocalDataSource(sharedPreferencesManager)
    }
}