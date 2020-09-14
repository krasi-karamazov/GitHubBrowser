package kpk.dev.domain.usecase

import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.domain.entity.ResponseModel
import kpk.dev.domain.repository.IGitHubBrowserRepo
import javax.inject.Inject

class GitHubBrowserUseCase @Inject constructor(private val repo: IGitHubBrowserRepo) :
    IGitHubBrowserUseCase {
    override suspend fun getRepositories(user: String, initialLoad: Boolean): ResponseModel<List<GitHubRepoItem>> = repo.getRepositories(user, initialLoad)

    override suspend fun getCommits(
        user: String,
        repoName: String
    ): ResponseModel<List<CommitItem>> = repo.getCommits(user, repoName)

    override suspend fun clearDb(): ResponseModel<Unit> = repo.clearDb()
}