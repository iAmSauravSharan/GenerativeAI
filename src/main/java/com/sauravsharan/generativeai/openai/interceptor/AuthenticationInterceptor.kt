package com.sauravsharan.generativeai.openai.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class AuthenticationInterceptor(private val token: String): Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
                .newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        return chain.proceed(request)
    }

}