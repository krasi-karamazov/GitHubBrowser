package kpk.dev.data.datasource.remote

import kpk.dev.data.api.ApiInitializer
import kpk.dev.data.api.entity.Dto
import kpk.dev.data.mappers.map
import kpk.dev.data.utils.safeApiCall
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel
import javax.inject.Inject

class GitHubBrowserRemoteDataSource @Inject constructor (private val apiInitializer: ApiInitializer) : IGitHubBrowserRemoteDataSource {
    override suspend fun getRepositories(user: String): ResponseModel<List<GitHubRepoItem>> {
        return when(val result = safeApiCall{ apiInitializer.getApiService().getRepositoriesForUser(user = user) }) {
            is ResponseModel.Success -> {
                ResponseModel.Success(result.responseData?.map {
                    it.map()
                })
            }
            is ResponseModel.Failure -> {
                result
            }
        }
    }

    override suspend fun getCommits(user: String, repoName: String): ResponseModel<List<CommitItem>> {
       return when(val result = safeApiCall{ apiInitializer.getApiService().getCommitsForUserAndRepo(user = user, repoName = repoName) }) {
            is ResponseModel.Success -> {
                ResponseModel.Success(result.responseData?.map {
                    it.map(repoName)
                })
            }
            is ResponseModel.Failure -> {
                result
            }
        }
    }
}