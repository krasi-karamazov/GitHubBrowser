package kpk.dev.data.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

sealed class GitHubBrowserEntity {
    @Entity(tableName = "repositories")
    data class Repository(
        @ColumnInfo(name = "name") var name: String = "",
        @ColumnInfo(name = "private") val isPrivate: Boolean,
        @ColumnInfo(name = "stars") val stars: Int,
        @ColumnInfo(name = "watchers") val watchers: Int,
        @ColumnInfo(name = "forks") val forks: Int,
        @ColumnInfo(name = "language") val language: String,
        @ColumnInfo(name = "image") val image: String,
        @ColumnInfo(name = "owner_name") val ownerName: String
    ) : GitHubBrowserEntity() {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }

    @Entity(tableName = "commits")
    data class Commit(
        @ColumnInfo(name = "author") val author: String,
        @ColumnInfo(name = "date") val date: String,
        @ColumnInfo(name = "message") val message: String,
        @ColumnInfo(name = "hash") val hash: String,
        @ColumnInfo(name = "repo_name") val repoName: String
    ) {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }
}
