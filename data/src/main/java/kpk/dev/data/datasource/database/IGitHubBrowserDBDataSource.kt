package kpk.dev.data.datasource.database

import kpk.dev.data.db.tables.GitHubBrowserEntity

interface IGitHubBrowserDBDataSource {

    fun getRepositories() : List<GitHubBrowserEntity.Repository>

    fun saveRepositoriesToDB(items: List<GitHubBrowserEntity.Repository>)

    fun getCommits(repoName: String) : List<GitHubBrowserEntity.Commit>

    fun saveCommitsToDB(items: List<GitHubBrowserEntity.Commit>)

    fun deleteRepository(repo: GitHubBrowserEntity.Repository)

    fun deleteAllRepositories()
}