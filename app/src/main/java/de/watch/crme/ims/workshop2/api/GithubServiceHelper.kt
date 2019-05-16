package de.watch.crme.ims.workshop2.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubServiceHelper {

    private const val BASE_URL = "https://api.github.com/"

    private var client : GithubService? = null

    fun getApiService() : GithubService? {

        if(client == null){
            client = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService::class.java)
        }

        return client
    }
}