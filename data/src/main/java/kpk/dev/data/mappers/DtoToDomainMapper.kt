package kpk.dev.data.mappers

import kpk.dev.data.api.entity.Dto
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import java.util.*

fun Dto.RepoEntity.map() = GitHubRepoItem(
    id?.toLong() ?: Math.random().toLong(),
    name ?: "repo name",
    private ?: false,
    stargazersCount ?: 0,
    watchersCount ?: 0,
    forksCount ?: 0,
    language ?: "Kotlin",
    emptyList(),
    owner?.avatarUrl ?: "none",
    owner?.login ?: "owner"
)

fun Dto.CommitEntity.map(repoName: String) = CommitItem(
    commit?.author?.name ?: "Author unknown",
    commit?.author?.date ?: Date().toString(),
    commit?.message ?: "Empty commit message",
    sha ?: "Empty sha",
    repoName
)