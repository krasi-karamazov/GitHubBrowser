package kpk.dev.domain.domain.entity

data class GitHubRepoItem(
    val id: Long,
    val name: String,
    val private: Boolean,

    //Rainbow Rising!!!
    val stargazers: Int,
    val watchers: Int,
    val forks: Int,
    val language: String,
    var commits:List<CommitItem>
)