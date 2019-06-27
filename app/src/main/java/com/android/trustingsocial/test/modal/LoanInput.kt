package com.android.trustingsocial.test.modal

import com.google.gson.annotations.SerializedName

data class LoanInput (
    @SerializedName("phone_number")         val phone : String,
    @SerializedName("full_name")            val name : String,
    @SerializedName("national_id_number")   val nationalId : String,
    @SerializedName("monthly_income")       val monthlyIncome : String,
    @SerializedName("province")             val province : String
)