package com.android.trustingsocial.test.network

import android.content.Context
import com.android.trustingsocial.test.BuildConfig
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.util.ApiConstant
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody
import timber.log.Timber

class MockingInterceptor(private var context : Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            Timber.d("intercept for uri: $uri")
            val responseString = when {
                uri.endsWith(ApiConstant.API_GET_BANK_INFO) -> getBankInformationJson()
                uri.endsWith(ApiConstant.API_GET_PROVINCE) -> getProvicesJson()
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(ApiConstant.API_RESPONSE_SUCCESS_CODE)
                .message(responseString)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()

        } else {
            return chain.proceed(chain.request())
        }
    }

    private fun getBankInformationJson() : String {
        return context.resources.openRawResource(R.raw.bankinfo).bufferedReader().use { it.readText() }
    }

    private fun getProvicesJson() : String {
        return context.resources.openRawResource(R.raw.provices).bufferedReader().use { it.readText() }
    }

}