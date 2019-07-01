package com.android.trustingsocial.test.repository

import android.app.Application
import com.android.trustingsocial.test.LoanApplication
import com.codding.test.startoverflowuser.network.CustomResult
import com.codding.test.startoverflowuser.network.LoanApiInterface
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

open class NetWorkBaseRepository {

    suspend fun <T: Any> safeApiCall(call: suspend () -> Response<T>) : CustomResult<T> {
        try {
            var respond = call.invoke()
            if (respond.isSuccessful) return CustomResult.Success(respond.body()!!)
            return CustomResult.Error(respond.code(), IOException(respond.message()))
        } catch (e : IOException) {
            return CustomResult.Error(0, IOException(e.message!!))
        }

    }
}