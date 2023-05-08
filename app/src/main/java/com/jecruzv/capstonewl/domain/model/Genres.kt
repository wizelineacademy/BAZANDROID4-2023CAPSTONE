package com.jecruzv.capstonewl.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jecruzv.capstonewl.util.Annotations
import com.jecruzv.capstonewl.util.Constants

@Annotations("Entregable 2")
@Entity(tableName = Constants.GENRES_TABLE)
data class Genres(
    @PrimaryKey
    val id: Int? = 0,
    val name: String? = null
)