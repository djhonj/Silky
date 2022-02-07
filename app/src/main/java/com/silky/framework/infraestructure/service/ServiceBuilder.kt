package com.silky.framework.infraestructure.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {
    //generador de servicios para realizar las peticiones
    private const val URL_API = "API"
    private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        .callTimeout(20, TimeUnit.MINUTES)
        .connectTimeout(20, TimeUnit.MINUTES)
        .writeTimeout(20, TimeUnit.MINUTES)
        .readTimeout(20, TimeUnit.MINUTES)
        .addInterceptor(logger)

    private val builder: Retrofit.Builder = Retrofit.Builder().baseUrl(URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit: Retrofit = builder.build()

    //function generica
    fun <T> buildService(serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }
}