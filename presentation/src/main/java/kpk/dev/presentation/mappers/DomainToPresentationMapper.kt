package kpk.dev.presentation.mappers

import kpk.dev.data.db.tables.GitHubBrowserEntity
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.presentation.model.CommitUiModel
import kpk.dev.presentation.model.GitHubRepoUiModel

fun GitHubRepoItem.map() = GitHubRepoUiModel(
    id,
    name,
    private,
    stargazers,
    watchers,
    forks,
    language ?: "",
    emptyList(),
    image,
    ownerName
)

fun CommitItem.map(repoName: String) = CommitUiModel(
    author,
    date,
    message,
    hash,
    repoName
)