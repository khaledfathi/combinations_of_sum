package com.kinsidelibs.find_combination.view_model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kinsidelibs.find_combination.R
import com.kinsidelibs.find_combination.core.Calculation
import java.util.Locale

class MainActivityViewModel : ViewModel() {
    //input data
    val _inputValues: MutableList<Int> = mutableListOf()
    val inputValues: List<Int> = _inputValues

    //result data
    private var _resultValues = MutableLiveData<List<List<Int>>>(listOf())
    val resultValues: LiveData<List<List<Int>>> = _resultValues

    //input counter
    private var _inputCounter = MutableLiveData<Int>(0)
    val inputCounter: LiveData<Int> = _inputCounter

    //combinations counter
    private var _combinationsCounter = MutableLiveData<Int>(0)
    var combinationCounter: LiveData<Int> = _combinationsCounter

    fun addInputValue(value: Int) {
        _inputValues.add(0, value)
    }

    fun removeInputValue(position: Int) {
        _inputValues.removeAt(position)
    }

    fun clearInputValues() {
        _inputValues.clear()
    }

    fun clearResultValues() {
        _resultValues.value = listOf()
    }


    fun resetInputCounter() {
        _inputCounter.value = 0
    }

    fun resetCombinationCounter() {
        _combinationsCounter.value = 0
    }

    fun changeCombinationsCounter() {
        _combinationsCounter.value = _resultValues.value?.size
    }

    fun getCombinationsCounter() = _resultValues.value?.size

    fun changeInputValueCounter() {
        _inputCounter.value = inputValues.size
    }

    fun getInputValueCounter(context: Context) =
        context.resources.getString(R.string.input_counter) + _inputCounter.value.toString()


    fun getCombinationsCounter(context: Context) =
        context.resources.getString(R.string.combinations_counter) + _combinationsCounter.value.toString()

    fun calc(target: Int) {
        _resultValues.value = Calculation.findCombinations(_inputValues, target)
    }
}