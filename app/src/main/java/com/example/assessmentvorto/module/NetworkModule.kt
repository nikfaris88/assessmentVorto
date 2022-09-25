package com.example.assessmentvorto.module

import androidx.annotation.VisibleForTesting
import com.example.assessmentvorto.api.ApiService
import com.serjltt.moshi.adapters.Wrapped
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
        val builder = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(false)
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                val request = builder.build()
                chain.proceed(request)
            }

        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)
        builder.writeTimeout(120, TimeUnit.SECONDS)

        return builder.build()

    }
//    companion object {
//        private var retrofit: Retrofit? = null
//        fun getApiClient(): Retrofit {
//            val gson = GsonBuilder()
//                .setLenient()
//                .create()
//
//            val okHttpClient = OkHttpClient.Builder()
//                .readTimeout(100, TimeUnit.SECONDS)
//                .connectTimeout(100, TimeUnit.SECONDS)
//                .build()
//
//            if (retrofit == null) {
//                retrofit = Retrofit.Builder()
//                    .baseUrl(BASEURL)
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create(gson))
//                    .build()
//            }
//
//            return retrofit!!
//        }
//    }


}