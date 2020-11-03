package com.fake.simplicity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ElementViewModel: ViewModel() {
    private val _element = MutableLiveData<Any>()
    var element: LiveData<Any> = _element

    fun postElement(position: Int){
        _element.value = DataRepository.getItem(position)
    }
}
