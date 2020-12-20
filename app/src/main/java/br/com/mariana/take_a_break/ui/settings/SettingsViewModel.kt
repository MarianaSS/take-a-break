package br.com.mariana.take_a_break.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingsViewModel : ViewModel() {

    private val timerValueMutable = MutableLiveData<Int>()
    var timerValue: LiveData<Int> = timerValueMutable

    fun updateValue(timerValue: Int) {
        timerValueMutable.postValue(timerValue)
    }
}