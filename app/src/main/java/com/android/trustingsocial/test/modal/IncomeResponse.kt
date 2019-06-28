package com.android.trustingsocial.test.modal

import com.google.gson.annotations.SerializedName

data class IncomeResponse (@SerializedName("data_list") val incomes : List<Income>)
