package kpk.dev.data.datasource.remote

import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel

interface IGitHubBrowserRemoteDataSource {

    suspend fun getRepositories(user: String): ResponseModel<List<GitHubRepoItem>>

    suspend fun getCommits(user: String, repoName: String):  ResponseModel<List<CommitItem>>
}