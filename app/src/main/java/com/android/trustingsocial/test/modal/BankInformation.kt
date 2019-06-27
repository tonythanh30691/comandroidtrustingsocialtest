package com.android.trustingsocial.test.modal

import com.google.gson.annotations.SerializedName

data class BankInformation (
    @SerializedName("name") val name : String,
    @SerializedName("logo") val logoUrl : String
)

