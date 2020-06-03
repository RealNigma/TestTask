package com.realnigma.testtask.dagger

import android.annotation.SuppressLint
import com.google.gson.*
import com.realnigma.testtask.network.EmployeeAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
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
            .build()
            .create(EmployeeAPI::class.java)
    }

    companion object {
        private const val BASE_URL = "https://gitlab.65apps.com/"
        var gson: Gson = GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(Date::class.java, object : JsonDeserializer<Date?> {
                val df: DateFormat = SimpleDateFormat("yyyy-MM-dd")
                @Throws(JsonParseException::class)
                override fun deserialize(
                    json: JsonElement,
                    typeOfT: Type?,
                    context: JsonDeserializationContext?
                ): Date? {
                    return try {
                        df.parse(json.asString)
                    } catch (e: ParseException) {
                        e.printStackTrace()
                        null
                    }
                }
            })
            .create()

    }


}