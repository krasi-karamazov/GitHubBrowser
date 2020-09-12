package kpk.dev.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import kpk.dev.data.db.tables.GitHubBrowserEntity

@Dao
interface GitHubRepoDao : BaseDao<GitHubBrowserEntity.Repository> {

    @Query("select * from repositories where id = :id")
    override fun select(id: Long): GitHubBrowserEntity.Repository

    @Query("select * from repositories")
    override fun select(): List<GitHubBrowserEntity.Repository>

    @Query("select * from repositories where name like :value")
    override fun search(value: String): List<GitHubBrowserEntity.Repository>

    @Query("delete from repositories where id between :firstId and :lastId")
    override fun deleteRange(firstId: Long, lastId: Long)

    @Query("delete from repositories")
    override fun truncate()
}