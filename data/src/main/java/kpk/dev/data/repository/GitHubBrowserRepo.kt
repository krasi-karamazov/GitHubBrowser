package kpk.dev.data.repository

import android.util.Log
import kotlinx.coroutines.withContext
import kpk.dev.data.datasource.database.IGitHubBrowserDBDataSource
import kpk.dev.data.datasource.remote.IGitHubBrowserRemoteDataSource
import kpk.dev.data.mappers.map
import kpk.dev.data.utils.CoroutineDispatcherProvider
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel
import kpk.dev.domain.repository.IGitHubBrowserRepo
import javax.inject.Inject

class GitHubBrowserRepo @Inject constructor(
    private val remoteDataSource: IGitHubBrowserRemoteDataSource,
    private val dbDataSource: IGitHubBrowserDBDataSource,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : IGitHubBrowserRepo {
    override suspend fun getRepositories(user: String, initialLoad: Boolean): ResponseModel<List<GitHubRepoItem>> {
        return withContext(coroutineDispatcherProvider.iO) {
            val result = dbDataSource.getRepositories()
            if(result.isNotEmpty() || initialLoad) {
                Log.d("Load", "Db")
                ResponseModel.Success(result.map { it.map() })
            } else {
                Log.d("Load", "remote")
                when (val remoteResult = remoteDataSource.getRepositories(user)) {
                    is ResponseModel.Success -> {
                        dbDataSource.saveRepositoriesToDB(remoteResult.responseData?.map { it.map() } ?: emptyList())
                        remoteResult
                    }
                    is ResponseModel.Failure -> {
                        remoteResult
                    }
                }
            }
        }
    }

    override suspend fun getCommits(
        user: String,
        repoName: String
    ): ResponseModel<List<CommitItem>> {
        return withContext(coroutineDispatcherProvider.iO) {
            val result = dbDataSource.getCommits(repoName)
            if(result.isNotEmpty()) {
                ResponseModel.Success(result.map { it.map(repoName) })
            } else {
                when (val remoteResult = remoteDataSource.getCommits(user, repoName)) {
                    is ResponseModel.Success -> {
                        dbDataSource.saveCommitsToDB(remoteResult.responseData?.map {
                            it.map(
                                repoName
                            )
                        } ?: emptyList())
                        remoteResult
                    }
                    is ResponseModel.Failure -> {
                        remoteResult
                    }
                }
            }
        }
    }

    override suspend fun clearDb(): ResponseModel<Unit> {
        return withContext(coroutineDispatcherProvider.iO) {
            ResponseModel.Success(dbDataSource.deleteAllRepositories())
        }
    }
}