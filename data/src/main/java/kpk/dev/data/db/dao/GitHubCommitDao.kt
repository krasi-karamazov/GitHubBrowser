package kpk.dev.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import kpk.dev.data.db.tables.GitHubBrowserEntity

@Dao
interface GitHubCommitDao : BaseDao<GitHubBrowserEntity.Commit> {
    @Query("select * from commits where id = :id")
    override fun select(id: Long): GitHubBrowserEntity.Commit

    @Query("select * from commits where id = :id")
    fun selectByRepoId(id: Long): List<GitHubBrowserEntity.Commit>

    @Query("select * from commits")
    override fun select(): List<GitHubBrowserEntity.Commit>

    @Query("select * from commits where hash like :value")
    override fun search(value: String): List<GitHubBrowserEntity.Commit>

    @Query("delete from commits where id between :firstId and :lastId")
    override fun deleteRange(firstId: Long, lastId: Long)

    @Query("delete from commits")
    override fun truncate()
}