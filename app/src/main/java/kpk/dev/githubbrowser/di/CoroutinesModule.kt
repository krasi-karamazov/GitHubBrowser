package kpk.dev.githubbrowser.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kpk.dev.data.utils.CoroutineDispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class CoroutinesModule {

    @Singleton
    @Provides
    fun provideCoroutinesDispatcherProvider(): CoroutineDispatcherProvider {
        return CoroutineDispatcherProvider()
    }
}