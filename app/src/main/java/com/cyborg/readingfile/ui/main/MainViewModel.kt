package com.cyborg.readingfile.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cyborg.readingfile.domain.DomainCity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*

class MainViewModel(app: Application) : AndroidViewModel(app) {
    // TODO: Implement the ViewModel
    private val context = getApplication<Application>().applicationContext
    private val _cities = MutableLiveData<List<DomainCity>?>()
    val cities: LiveData<List<DomainCity>?>
        get() = _cities
    private var cityString: String = ""

    //val cities2: List<DomainCity>


    private fun convert() {
        val moshi: Moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java ,DomainCity::class.java)
        val adapter: JsonAdapter<List<DomainCity>> = moshi.adapter(type)
        _cities.value = adapter.fromJson(cityString)
    }


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    init {
        readTextFile()
    }

    private fun readTextFile(){
        coroutineScope.launch {
            readFileFromAssets()
            convert()
        }}

    private suspend fun readFileFromAssets() {
        withContext(Dispatchers.IO) {
            cityString = context.assets.open("city.list.json").bufferedReader().use { it.readText() }
        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
