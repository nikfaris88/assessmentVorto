package com.example.assessmentvorto.module

import androidx.annotation.VisibleForTesting
import com.example.assessmentvorto.api.ApiService
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


const val BASEURL = "https://api.yelp.com/v3/"

@Module
class NetworkModule {

    @Provides
    @Singleton
    @VisibleForTesting
    fun provideAPI(okHttpClient: OkHttpClient): ApiService {
        val moshi = Moshi.Builder()
            .add(Wrapped.ADAPTER_FACTORY)
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(ApiService::class.java)
    }


    @Provides
    @Singleton
    @VisibleForTesting
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(false)
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                val header = builder.header(
                    "Authorization",
                    "Bearer " + "5E2j1eggecq-M4HHr3DY5pRPMtagGN3reLk3BQ4JcjzeBmv1p_mTEE8wA8SKK7AR4f7hAqvqwjM_LtURDHsjFcdXn09zKZ4rMZu9g3Gw1mN4XlkFuYBloi0CZz4rY3Yx"
                )
                val request = header.build()
                chain.proceed(request)
            }

        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.writeTimeout(120, TimeUnit.SECONDS)

        return builder.build()

    }
}