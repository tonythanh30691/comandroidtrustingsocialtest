package com.codding.test.startoverflowuser.network

import com.android.trustingsocial.test.modal.BankResponse
import com.android.trustingsocial.test.modal.LoanInput
import com.android.trustingsocial.test.modal.LoanResponse
import com.android.trustingsocial.test.modal.ProviceResponse
import com.android.trustingsocial.test.util.ApiConstant
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoanApiInterface {

    @GET(ApiConstant.API_GET_BANK_INFO)
    fun getBankInformation() : Deferred<Response<BankResponse>>

    @GET(ApiConstant.API_GET_PROVINCE)
    fun getProvince() : Deferred<Response<ProviceResponse>>

    @POST(ApiConstant.API_REGIS_LOAN)
    fun regisLoan(@Body loanInput: LoanInput) : Deferred<Response<LoanResponse>>
}