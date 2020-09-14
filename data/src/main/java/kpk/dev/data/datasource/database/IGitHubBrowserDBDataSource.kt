package kpk.dev.data.datasource.database

import kpk.dev.data.db.tables.GitHubBrowserEntity

interface IGitHubBrowserDBDataSource {

    suspend fun getRepositories() : List<GitHubBrowserEntity.Repository>

    suspend fun saveRepositoriesToDB(items: List<GitHubBrowserEntity.Repository>)

    suspend fun getCommits(repoName: String) : List<GitHubBrowserEntity.Commit>

    suspend fun saveCommitsToDB(items: List<GitHubBrowserEntity.Commit>)

    suspend fun deleteRepository(repo: GitHubBrowserEntity.Repository)

    suspend fun deleteAllRepositories()
}