package com.jecruzv.capstonewl.data.model.database
import com.jecruzv.capstonewl.data.model.MoviesDao
import com.jecruzv.capstonewl.util.Annotations
import javax.inject.Inject

@Annotations("Entregable 2")
class DataBaseRepository @Inject constructor(private val movieDao: MoviesDao) {

}