package kpk.dev.presentation.mappers

import kpk.dev.domain.entity.CommitItem
import kpk.dev.domain.entity.GitHubRepoItem
import kpk.dev.presentation.model.CommitUiModel
import kpk.dev.presentation.model.GitHubRepoUiModel
import java.text.SimpleDateFormat
import java.util.*

fun GitHubRepoItem.map() = GitHubRepoUiModel(
    id,
    name,
    private,
    stargazers,
    watchers,
    forks,
    language ?: "",
    null,
    image,
    ownerName
)

fun CommitItem.map(repoName: String) = CommitUiModel(
    author,
    formatDate(date),
    message,
    hash,
    repoName
)

private fun formatDate(dateString: String): String {
    val serverDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
    val uiDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault())
    val serverDate = serverDateFormat.parse(dateString)
    return uiDateFormat.format(serverDate!!)
}