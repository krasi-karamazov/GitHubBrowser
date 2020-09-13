package kpk.dev.data.api

import kpk.dev.data.api.entity.Dto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService  {

    @GET("/users/{user}/repos")
    fun getRepositoriesForUser(@Path("user") user: String): List<Dto.RepoEntity>

    @GET("/repos/{user}/{repo_name}/commits")
    fun getCommitsForUserAndRepo(@Path("user") user: String, @Path("repo_name") repoName: String): List<Dto.CommitEntity>
}