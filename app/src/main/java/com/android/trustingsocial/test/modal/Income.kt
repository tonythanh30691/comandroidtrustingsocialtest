package com.android.trustingsocial.test.modal

import com.google.gson.annotations.SerializedName

data class Income (
    @SerializedName("id")           val id : String,
    @SerializedName("description")  val description : String
    )