package com.sphinx.technicalservice.services

import com.sphinx.technicalservice.utils.Config
import com.sphinx.technicalservice.utils.Globals
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    private val baseUrl = Config.API_URL


    fun create(): TechnicalServiceApi {

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${Globals.token}")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        return retrofit.create(TechnicalServiceApi::class.java);
    }

}