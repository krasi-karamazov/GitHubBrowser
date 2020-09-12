package kpk.dev.domain.domain.usecase

import kpk.dev.domain.domain.entity.CommitItem
import kpk.dev.domain.domain.entity.GitHubRepoItem
import kpk.dev.domain.domain.entity.ResponseModel
import kpk.dev.domain.domain.repository.IGitHubBrowserRepo
import javax.inject.Inject

class GitHubBrowserUseCase @Inject constructor(private val repo: IGitHubBrowserRepo) :
    IGitHubBrowserUseCase {
    override suspend fun getRepositories(user: String): ResponseModel<List<GitHubRepoItem>> = repo.getRepositories(user)

    override suspend fun getCommits(
        user: String,
        repoName: String
    ): ResponseModel<List<CommitItem>> = repo.getCommits(user, repoName)


}