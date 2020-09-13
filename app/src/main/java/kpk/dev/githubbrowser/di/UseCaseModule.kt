package kpk.dev.githubbrowser.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kpk.dev.domain.usecase.GitHubBrowserUseCase
import kpk.dev.domain.usecase.IGitHubBrowserUseCase

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCaseModule {

    @Binds
    abstract fun bindGitHubBrowserUseCase(gitHubBrowserUseCase: GitHubBrowserUseCase): IGitHubBrowserUseCase
}