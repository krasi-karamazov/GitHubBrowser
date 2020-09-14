package kpk.dev.githubbrowser.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kpk.dev.presentation.utils.SHARED_PREFS_NAME
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {
    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext applicationContext: Context): SharedPreferences =
        applicationContext.getSharedPreferences(
            SHARED_PREFS_NAME, Context.MODE_PRIVATE
        )
}