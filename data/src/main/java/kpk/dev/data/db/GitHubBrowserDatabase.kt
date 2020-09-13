package kpk.dev.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import kpk.dev.data.db.dao.GitHubCommitDao
import kpk.dev.data.db.dao.GitHubRepoDao
import kpk.dev.data.db.tables.GitHubBrowserEntity

@Database(entities = [GitHubBrowserEntity.Repository::class, GitHubBrowserEntity.Commit::class], version = 1, exportSchema = false)
abstract class GitHubBrowserDatabase : RoomDatabase() {
    abstract fun gitHubRepoDao(): GitHubRepoDao
    abstract fun gitHubCommitDao(): GitHubCommitDao
}