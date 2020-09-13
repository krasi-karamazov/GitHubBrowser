package kpk.dev.data.datasource.database

import kpk.dev.data.db.dao.GitHubCommitDao
import kpk.dev.data.db.dao.GitHubRepoDao
import kpk.dev.data.db.tables.GitHubBrowserEntity
import javax.inject.Inject

class GitHubBrowserDBDataSource @Inject constructor(private val gitHubRepoDao: GitHubRepoDao, private val gitHubCommitDao: GitHubCommitDao) : IGitHubBrowserDBDataSource {

    override fun getRepositories(): List<GitHubBrowserEntity.Repository> = gitHubRepoDao.select()

    override fun saveRepositoriesToDB(items: List<GitHubBrowserEntity.Repository>) = gitHubRepoDao.insertList(items)

    override fun getCommits(repoName: String): List<GitHubBrowserEntity.Commit> = gitHubCommitDao.selectByRepoName(repoName)

    override fun saveCommitsToDB(items: List<GitHubBrowserEntity.Commit>) = gitHubCommitDao.insertList(items)

    override fun deleteRepository(repo: GitHubBrowserEntity.Repository) = gitHubRepoDao.delete(repo)

    override fun deleteAllRepositories() = gitHubRepoDao.truncate()
}