package de.watch.crme.ims.workshop2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Owner(
    @Expose
    val id : Int,
    @Expose
    val url : String,
    @Expose
    @SerializedName("login")
    val name : String,
    @Expose
    val type : String,
    @Expose
    @SerializedName("avatar_url")
    val avatar : String
)