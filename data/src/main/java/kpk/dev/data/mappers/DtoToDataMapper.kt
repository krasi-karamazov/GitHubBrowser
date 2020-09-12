package kpk.dev.data.mappers

import kpk.dev.data.db.tables.GitHubBrowserEntity
import kpk.dev.data.api.entity.Dto

fun Dto.RepoEntity.map() = GitHubBrowserEntity.Repository(
    name,
    private,
    stargazersCount,
    watchersCount,
    forksCount,
    language
)

fun Dto.CommitEntity.map(repoId: Long) = GitHubBrowserEntity.Commit(
    commit.author.name,
    commit.author.date,
    commit.message,
    sha,
    repoId
)