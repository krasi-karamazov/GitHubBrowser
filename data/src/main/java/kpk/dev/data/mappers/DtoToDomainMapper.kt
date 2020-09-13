package kpk.dev.data.mappers

import kpk.dev.data.api.entity.Dto
import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem

fun Dto.RepoEntity.map() = GitHubRepoItem(
    id.toLong(),
    name,
    private,
    stargazersCount,
    watchersCount,
    forksCount,
    language,
    emptyList(),
    owner.avatarUrl,
    owner.login
)

fun Dto.CommitEntity.map(repoName: String) = CommitItem(
    commit.author.name,
    commit.author.date,
    commit.message,
    sha,
    repoName
)