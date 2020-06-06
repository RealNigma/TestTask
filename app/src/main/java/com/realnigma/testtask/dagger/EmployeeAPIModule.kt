package com.realnigma.testtask.dagger

import com.google.gson.*
import com.realnigma.testtask.utils.DateDeserializer
import com.realnigma.testtask.network.EmployeeAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton


@Module
class EmployeeAPIModule {

    @Singleton
    @Provides
    fun provideApi(): EmployeeAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(EmployeeAPI::class.java)
    }

    companion object {
        private const val BASE_URL = "https://gitlab.65apps.com/"
        var gson: Gson = GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Date::class.java, DateDeserializer())
            .create()
    }

}