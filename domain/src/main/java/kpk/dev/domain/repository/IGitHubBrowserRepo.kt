package kpk.dev.domain.repository

import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel

interface IGitHubBrowserRepo {

    suspend fun getRepositories(
        user: String,
        initialLoad: Boolean
    ): ResponseModel<List<GitHubRepoItem>>

    suspend fun getCommits(user: String, repoName: String): ResponseModel<List<CommitItem>>

    suspend fun clearDb(): ResponseModel<Unit>
}