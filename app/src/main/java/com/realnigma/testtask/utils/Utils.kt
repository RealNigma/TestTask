package com.realnigma.testtask.utils

import android.annotation.SuppressLint
import android.widget.ImageView
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.squareup.picasso.Picasso
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun loadImage(url: String?, imageView: ImageView) {
    if (url != null && url.isNotEmpty()) {
        Picasso.get().load(url).into(imageView)
    }
}

@SuppressLint("SimpleDateFormat")
fun  convertDate(date: Date): String {
    val timeFormatter = SimpleDateFormat("dd.MM.yyyy")
    return timeFormatter.format(date)
}

@SuppressLint("DefaultLocale")
fun getFullName(firstName: String?, lastName : String?) : String {
    val formatedFirstName = firstName?.toLowerCase()?.capitalize()
    val formatedLastName = lastName?.toLowerCase()?.capitalize()
    return "$formatedFirstName $formatedLastName"
}

fun calculateAge(date : Date?): String {
    if (date == null) return "««"
    val ageDate = Calendar.getInstance()
    val todayDate = Calendar.getInstance()
    ageDate.time = date
    val year = ageDate[Calendar.YEAR]
    val month = ageDate[Calendar.MONTH]
    val day = ageDate[Calendar.DAY_OF_MONTH]

    ageDate[year, month + 1] = day
    var age = todayDate[Calendar.YEAR] - ageDate[Calendar.YEAR]

    if (todayDate[Calendar.DAY_OF_YEAR] < ageDate[Calendar.DAY_OF_YEAR]) {
        age--
    }

    val ageString = age.toString()
    var yearsText = "лет"
    val lastIndex = ageString.lastIndex
           when {
               ageString[lastIndex] == '1' -> yearsText = "год"
               ageString[lastIndex] in '2'..'4' -> yearsText = "года"
               (ageString[lastIndex] == '0'
                       || ageString[1] in '5'..'9') -> yearsText = "лет"
           }

    return "$age $yearsText"
}

open class DateDeserializer : JsonDeserializer<Date?> {
    @SuppressLint("SimpleDateFormat")
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        var dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy" )
        if (json.asString != null && json.asString != """""") {

            val separators = arrayOf('-', '/', '.')
            when {
                (json.asString[5] in separators && json.asString[2] in separators)
                    -> { dateFormat = SimpleDateFormat("dd${json.asString[2]}MM${json.asString[2]}yyyy" )
                }
                (json.asString[4] in separators && json.asString[7] in separators)
                -> dateFormat = SimpleDateFormat("yyyy${json.asString[4]}MM${json.asString[4]}dd" )
            }
        }
        return try {
            dateFormat.parse(json.asString)
        } catch (parseException: ParseException) {
            parseException.printStackTrace()
            null
        }
    }
}