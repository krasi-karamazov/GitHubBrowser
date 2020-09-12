package kpk.dev.data.db.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

sealed class GitHubBrowserEntity {
    @Entity(tableName = "repositories")
    data class Repository(
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "is_private") var private: Boolean,
        @ColumnInfo(name = "stars") var stars: Int,
        @ColumnInfo(name = "watchers") var watchers: Int,
        @ColumnInfo(name = "forks") var forks: Int,
        @ColumnInfo(name = "language") var language: String
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
        @ColumnInfo(name = "repo_id") val repoId: Long
    ) {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }
}
