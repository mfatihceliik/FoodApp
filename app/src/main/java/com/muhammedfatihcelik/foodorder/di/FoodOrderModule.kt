package com.muhammedfatihcelik.foodorder.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.muhammedfatihcelik.foodorder.data.local.SharedPreferenceManager
import com.muhammedfatihcelik.foodorder.data.remote.FoodOrderApiService
import com.muhammedfatihcelik.foodorder.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class FoodOrderModule {


    @Provides
    fun provideFoodOrderApiService(@FoodOrderApiRetrofit retrofit: Retrofit): FoodOrderApiService {
        return retrofit.create(FoodOrderApiService::class.java)
    }

    @Provides
    @FoodOrderApiRetrofit
    fun provideFoodOrderRetrofit(@FoodOrderApiHttpClient okHttpClient: OkHttpClient, @FoodOrderApiEndPoint url: String, @FoodOrderApiGsonFactory gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    /*@Provides
    @FoodOrderApiHttpClient
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }*/

    @Provides
    @FoodOrderApiGsonFactory
    fun provideGsonFactory() : Gson {
        return Gson()
    }

    @Provides
    @FoodOrderApiEndPoint
    fun provideEndPoint(): String {
        return "http://192.168.1.20:8080/api/"
    }

    @Provides
    fun remoteDataSource(apiService: FoodOrderApiService) : RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    /*@Provides
    @FoodOrderApiHttpClient
    fun provideAuthentication(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(Interceptor {
            var token = SharedPreferenceManager(context).getToken()
            var request = it.request().newBuilder().addHeader("Authorization", "$token").build()
            it.proceed(request = request)
        })
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
        return builder.build()
    }*/

    /*@Provides
    fun provideContext(context: Context) : Context {
        return context.applicationContext
    }*/

    @Provides
    @FoodOrderApiHttpClient
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level = if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        })
        builder.addInterceptor(Interceptor {
            var token = SharedPreferenceManager(context).getToken()
            var request = it.request().newBuilder().addHeader("authorization", "$token").build() //.addHeader("Authorization", "$token").build()192
            it.proceed(request = request)
        })
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
        return builder.build()
    }
}



@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FoodOrderApiGsonFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FoodOrderApiEndPoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FoodOrderApiHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class FoodOrderApiRetrofit

