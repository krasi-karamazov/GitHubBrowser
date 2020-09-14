package kpk.dev.data.api

import kpk.dev.data.api.entity.Dto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService  {

    @GET("/users/{user}/repos")
    suspend fun getRepositoriesForUser(@Header("Authorization") token: String = "7f7dabf161cb3856fe3415494bb8ce82a578486d", @Path("user") user: String, @Query("per_page") numRepos: Int = 5, @Query("page") page: Int = 1): List<Dto.RepoEntity>

    @GET("/repos/{user}/{repo_name}/commits")
    suspend fun getCommitsForUserAndRepo(@Header("Authorization") token: String = "7f7dabf161cb3856fe3415494bb8ce82a578486d", @Path("user") user: String, @Path("repo_name") repoName: String): List<Dto.CommitEntity>
}