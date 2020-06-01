package com.realnigma.testtask.dagger

import com.realnigma.testtask.network.EmployeeAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class EmployeeAPIModule {

    @Singleton
    @Provides
    fun provideApi(): EmployeeAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EmployeeAPI::class.java)
    }

    companion object {
        private const val BASE_URL = "https://gitlab.65apps.com/"
    }


}