package com.android.trustingsocial.test.modal

import com.google.gson.annotations.SerializedName

data class ProviceResponse (
    @SerializedName("total") val total : Int,
    @SerializedName("provinces_list") val provinces : List<String>
)