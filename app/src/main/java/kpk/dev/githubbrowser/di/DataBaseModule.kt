package kpk.dev.githubbrowser.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kpk.dev.data.db.GitHubBrowserDatabase
import kpk.dev.data.db.dao.GitHubCommitDao
import kpk.dev.data.db.dao.GitHubRepoDao
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): GitHubBrowserDatabase {
        return Room.databaseBuilder(
            appContext,
            GitHubBrowserDatabase::class.java,
            "git_hub_browser.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRepositoriesDao(db: GitHubBrowserDatabase): GitHubRepoDao = db.gitHubRepoDao()

    @Singleton
    @Provides
    fun provideCommitsDao(db: GitHubBrowserDatabase): GitHubCommitDao = db.gitHubCommitDao()
}