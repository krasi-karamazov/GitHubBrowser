package kpk.dev.data.mappers

import kpk.dev.data.db.tables.GitHubBrowserEntity
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem

fun GitHubRepoItem.map() = GitHubBrowserEntity.Repository(
    name,
    private,
    stargazers,
    watchers,
    forks,
    language,
    image,
    ownerName
)

fun CommitItem.map(repoName: String) = GitHubBrowserEntity.Commit(
    author,
    date,
    message,
    hash,
    repoName
)