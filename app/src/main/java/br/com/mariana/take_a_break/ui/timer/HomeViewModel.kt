package br.com.mariana.take_a_break.ui.timer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val timerMutableLiveData = MutableLiveData<Int>().apply {
        value = 100000
    }
    val text: LiveData<Int> = timerMutableLiveData
}