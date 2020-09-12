package kpk.dev.data.mappers

import kpk.dev.data.db.tables.GitHubBrowserEntity
import kpk.dev.domain.domain.entity.CommitItem
import kpk.dev.domain.domain.entity.GitHubRepoItem

fun GitHubBrowserEntity.Repository.map() = GitHubRepoItem(
    id,
    name,
    private,
    starts,
    watchers,
    forks,
    language,
    emptyList()
)

fun GitHubBrowserEntity.Commit.map() = CommitItem(
    author,
    date,
    message,
    hash
)