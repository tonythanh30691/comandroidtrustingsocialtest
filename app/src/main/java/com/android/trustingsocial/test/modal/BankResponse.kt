package com.android.trustingsocial.test.modal


import com.google.gson.annotations.SerializedName

data class BankResponse (
    @SerializedName("min_amount") val minAmount : Int,
    @SerializedName("max_amount") val maxAmount : Int,
    @SerializedName("min_tenor")  val minTenor : Int,
    @SerializedName("max_tenor")  val maxTenor : Int,
    @SerializedName("interest_rate")  val rate : Float,
    @SerializedName("bank")       val bank : BankInformation
)