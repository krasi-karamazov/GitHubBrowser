package kpk.dev.data.mappers

import kpk.dev.data.db.tables.GitHubBrowserEntity
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem

fun GitHubBrowserEntity.Repository.map() = GitHubRepoItem(
    id,
    name,
    isPrivate,
    stars,
    watchers,
    forks,
    language,
    emptyList(),
    image,
    ownerName
)

fun GitHubBrowserEntity.Commit.map(repoName: String) = CommitItem(
    author,
    date,
    message,
    hash,
    repoName
)