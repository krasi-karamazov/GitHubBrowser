package kpk.dev.data.api

import kpk.dev.data.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiInitializer @Inject constructor() {
    private var retrofitInstance: Retrofit

    init {
        retrofitInstance = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val bodyLoggingInterceptor = HttpLoggingInterceptor()
        bodyLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(bodyLoggingInterceptor)
            .build()
    }

    fun getApiService(): ApiService = retrofitInstance.create(ApiService::class.java)
}