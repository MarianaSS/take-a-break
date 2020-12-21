package br.com.mariana.take_a_break.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Em breve você poderá ver uma análise de sua produtividade ao longo da semana. Aguarde!"
    }
    val text: LiveData<String> = _text
}