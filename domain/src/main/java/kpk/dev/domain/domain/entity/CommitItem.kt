package kpk.dev.domain.domain.entity

data class CommitItem(
    val author: String,
    val date: String,
    val message: String,
    val hash: String
)