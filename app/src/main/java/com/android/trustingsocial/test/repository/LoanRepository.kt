package com.android.trustingsocial.test.repository

import com.android.trustingsocial.test.modal.BankResponse
import com.android.trustingsocial.test.modal.LoanInput
import com.android.trustingsocial.test.modal.LoanResponse
import com.android.trustingsocial.test.modal.ProviceResponse
import com.codding.test.startoverflowuser.network.CustomResult
import com.codding.test.startoverflowuser.network.LoanApiInterface

class LoanRepository (private var apiService: LoanApiInterface) : NetWorkBaseRepository() {

    suspend fun getBankInformation(): CustomResult<BankResponse> {
        return safeApiCall(
            call = { apiService.getBankInformation().await() }
        )
    }

    suspend fun getProvinves(): CustomResult<ProviceResponse> {
        return safeApiCall(
            call = { apiService.getProvince().await() }
        )
    }

    suspend fun regisLoan(loanInput: LoanInput): CustomResult<LoanResponse> {
        return safeApiCall(
            call = { apiService.regisLoan(loanInput).await() }
        )
    }


}