package de.watch.crme.ims.workshop2.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repository(
    @Expose
    val id : Int,
    @Expose
    val name : String,
    @Expose
    val private : Boolean,
    @Expose
    val description : String,
    @Expose
    @SerializedName("html_url")
    val url : String,
    @Expose
    val owner : Owner)