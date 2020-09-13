package kpk.dev.domain.entity

data class GitHubRepoItem(
    val id: Long,
    val name: String,
    val private: Boolean,

    //Rainbow Rising!!!
    val stargazers: Int,
    val watchers: Int,
    val forks: Int,
    val language: String,
    var commits:List<CommitItem>,
    var image:String,
    var ownerName: String
) : DomainEntity