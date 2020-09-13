package kpk.dev.githubbrowser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kpk.dev.data.datasource.database.GitHubBrowserDBDataSource
import kpk.dev.data.datasource.database.IGitHubBrowserDBDataSource
import kpk.dev.data.datasource.remote.GitHubBrowserRemoteDataSource
import kpk.dev.data.datasource.remote.IGitHubBrowserRemoteDataSource

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSource: GitHubBrowserRemoteDataSource): IGitHubBrowserRemoteDataSource

    @Binds
    abstract fun bindDBDataSource(dbDataSource: GitHubBrowserDBDataSource): IGitHubBrowserDBDataSource
}