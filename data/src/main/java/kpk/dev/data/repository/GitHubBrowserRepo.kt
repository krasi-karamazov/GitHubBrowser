package kpk.dev.data.repository

import kpk.dev.data.datasource.database.IGitHubDBDataSource
import kpk.dev.data.datasource.remote.ISupportDeskRemoteDataSource
import kpk.dev.data.utils.CoroutineDispatcherProvider
import kpk.dev.domain.domain.entity.SupportDeskListItem
import kpk.dev.domain.domain.repository.IGitHubBrowserRepo
import kpk.dev.domain.domain.entity.CommitItem
import kpk.dev.domain.domain.entity.GitHubRepoItem
import kpk.dev.domain.domain.entity.ResponseModel
import javax.inject.Inject

class GitHubBrowserRepo @Inject constructor(
    private val remoteDataSource: ISupportDeskRemoteDataSource,
    private val dbDataSource: IGitHubDBDataSource,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : IGitHubBrowserRepo {
    override suspend fun getRepositories(user: String): ResponseModel<List<GitHubRepoItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommits(
        user: String,
        repoName: String
    ): ResponseModel<List<CommitItem>> {
        TODO("Not yet implemented")
    }

    /*override suspend fun getSupportDeskUsers(): ResponseModel<List<SupportDeskListItem>> {
        return withContext(coroutineDispatcherProvider.iO) {
            val result = dbDataSource.getSupportDeskList()
            if (result.isNotEmpty()) {
                ResponseModel.Success(result.map { it.map() })
            } else {

                when (val remoteResult = remoteDataSource.getSupportDeskUsers()) {
                    is ResponseModel.Success -> {
                        dbDataSource.saveToDB(remoteResult.responseData?.map { it.map() } ?: emptyList())
                        remoteResult
                    }
                    is ResponseModel.Failure -> {
                        remoteResult
                    }
                }
            }
        }
    }

    override suspend fun searchSupportDeskUsersByName(name: String): ResponseModel<List<SupportDeskListItem>> {
        return withContext(coroutineDispatcherProvider.iO) {
            val result = dbDataSource.searchByName(name)
            ResponseModel.Success(result.map { it.map() })
        }
    }*/
}