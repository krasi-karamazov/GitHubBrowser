package kpk.dev.domain.domain.repository

import kpk.dev.domain.domain.entity.CommitItem
import kpk.dev.domain.domain.entity.GitHubRepoItem
import kpk.dev.domain.domain.entity.ResponseModel

interface IGitHubBrowserRepo {

    suspend fun getRepositories(user: String): ResponseModel<List<GitHubRepoItem>>

    suspend fun getCommits(user: String, repoName: String): ResponseModel<List<CommitItem>>
}