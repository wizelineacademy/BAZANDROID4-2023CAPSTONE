package com.jecruzv.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jecruzv.local.Constants

@Entity(tableName = Constants.GENRES_TABLE)
data class Genres(
    @PrimaryKey
    val id: Int? = 0,
    val name: String? = null
)