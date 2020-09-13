package kpk.dev.githubbrowser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kpk.dev.data.repository.GitHubBrowserRepo
import kpk.dev.domain.repository.IGitHubBrowserRepo

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGitHubBrowserRepo(gitHubBrowserRepo: GitHubBrowserRepo): IGitHubBrowserRepo
}