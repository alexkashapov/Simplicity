package com.fake.simplicity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ElementsViewModel(): ViewModel() {

    private var _elements = MutableLiveData<List<Any>>()
    val elements: LiveData<List<Any>> = _elements

    fun generateRandomData(randomCreator: RandomCreator,types: List<Class<*>>){
        _elements.value = DataRepository.createRandomData(randomCreator,types)
    }

}