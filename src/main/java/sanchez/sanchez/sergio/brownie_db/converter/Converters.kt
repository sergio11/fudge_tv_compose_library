package sanchez.sanchez.sergio.brownie_db.converter

import androidx.room.TypeConverter
import java.util.*

/**
 * Converters
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }



}