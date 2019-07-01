package com.android.trustingsocial.test.network

import android.content.Context
import com.android.trustingsocial.test.BuildConfig
import com.android.trustingsocial.test.R
import com.android.trustingsocial.test.di.component.DaggerGsonComponent
import com.android.trustingsocial.test.modal.LoanInput
import com.android.trustingsocial.test.modal.LoanResponse
import com.android.trustingsocial.test.util.ApiConstant
import com.android.trustingsocial.test.util.JsonHelper
import okhttp3.*
import timber.log.Timber
import okhttp3.RequestBody
import java.io.IOException
import javax.inject.Inject


class MockingInterceptor(private var context : Context) : Interceptor {

    @Inject lateinit var jsonHelper: JsonHelper

    init {
        DaggerGsonComponent.builder().build().inject(this)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            Timber.d("intercept for uri: $uri")
            val responseString = when {
                uri.endsWith(ApiConstant.API_GET_BANK_INFO) -> jsonHelper.loadJsonFromRaw(context, R.raw.bankinfo)
                uri.endsWith(ApiConstant.API_GET_PROVINCE) -> jsonHelper.loadJsonFromRaw(context, R.raw.provices)
                uri.endsWith(ApiConstant.API_REGIS_LOAN) -> generateLoanRespondString(chain.request())
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

    private fun bodyToString(request: RequestBody?): String {
            try {
                val buffer = okio.Buffer()
                if (request != null) {
                    request.writeTo(buffer)
                    return buffer.readUtf8()
                }
            } catch (e: IOException) {
                Timber.e(e)
            }
        return ""
    }

    private fun generateLoanRespondString(request: Request) : String {
        var bodyString = bodyToString(request.body())
        var loanInput : LoanInput = jsonHelper.convertJsonToClass(bodyString)
        var loanResponse  = LoanResponse(
                                id = System.currentTimeMillis().toString(), // Fake ID
                                name = loanInput.name,
                                phone = loanInput.phone,
                                nationalId = loanInput.nationalId,
                                monthlyIncome = loanInput.monthlyIncome,
                                province = loanInput.province
                            )
        return jsonHelper.convertClassToJson(loanResponse)
    }

}