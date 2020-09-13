package kpk.dev.data.datasource.remote

import kpk.dev.data.api.ApiInitializer
import kpk.dev.data.api.entity.Dto
import kpk.dev.data.mappers.map
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel
import javax.inject.Inject

class GitHubBrowserRemoteDataSource @Inject constructor (private val apiInitializer: ApiInitializer) : IGitHubBrowserRemoteDataSource {
    override suspend fun getRepositories(user: String): ResponseModel<List<GitHubRepoItem>> {
        val list: List<Dto.RepoEntity>? = apiInitializer.getApiService().getRepositoriesForUser(user)
        return if (!list.isNullOrEmpty()) {
            ResponseModel.Success(list.map {
                it.map()
            })
        } else {
            ResponseModel.Failure("Could not fetch staff data")
        }
    }

    override suspend fun getCommits(user: String, repoName: String): ResponseModel<List<CommitItem>> {
        val list: List<Dto.CommitEntity>? = apiInitializer.getApiService().getCommitsForUserAndRepo(user, repoName)
        return if (!list.isNullOrEmpty()) {
            ResponseModel.Success(list.map {
                it.map(repoName)
            })
        } else {
            ResponseModel.Failure("Could not fetch staff data")
        }
    }
}