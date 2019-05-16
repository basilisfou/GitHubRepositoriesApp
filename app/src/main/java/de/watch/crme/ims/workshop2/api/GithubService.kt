package de.watch.crme.ims.workshop2.api

import de.watch.crme.ims.workshop2.model.Repository
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("repositories")
    fun getRepositories() : Call<List<Repository>>
}