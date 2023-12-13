package com.example.chapter06.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapter06.R
import com.example.chapter06.Repository
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class DistancesViewModel(private val repository: Repository): ViewModel() {
    private val _unit: MutableLiveData<Int> = MutableLiveData(
        repository.getInt("unit", R.string.meter)
    )

    val unit: LiveData<Int>
        get() = _unit

    fun setUnit(value: Int){
        _unit.value = value
        repository.putInt("unit", value)
    }

    private val _distance: MutableLiveData<String> = MutableLiveData(
        repository.getString("distance", ""))

    val distance: LiveData<String>
        get() = _distance

    fun getDistanceAsFloat(): Float = (_distance.value ?: "").let {
        return try {
            it.toFloat()
        }catch (e: NumberFormatException){
            Float.NaN
        }
    }

    fun setDistance(value: String){
        _distance.value = value
        repository.putString("distance", value)
    }

    private val _convertedDistance: MutableLiveData<Float> = MutableLiveData(Float.NaN)

    val convertedDistance:LiveData<Float>
        get() = _convertedDistance

    fun convert(){
        getDistanceAsFloat().let {
            viewModelScope.launch {
                _convertedDistance.value = if(!it.isNaN())
                    if(_unit.value == R.string.meter)
                        it * 0.00062137F
                    else
                        it / 0.00062137F
                else
                    Float.NaN
            }
        }
    }
}