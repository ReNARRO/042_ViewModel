package com.example.pertemuan4

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pertemuan4.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel: ViewModel() {
    var namaUsr : String by mutableStateOf("")
        private set
    var notlp : String by mutableStateOf("")
        private set
    var alamat : String by mutableStateOf("")
        private set
    var jeniskl : String by mutableStateOf("")
        private set
    var email : String by mutableStateOf("")
        private set
    var status : String by mutableStateOf("")
        private set
    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()

    fun insertData(nm:String, tlp:String, jk:String, almt:String, em:String, st: String){
        namaUsr=nm;
        notlp=tlp;
        alamat=almt
        jeniskl=jk;
        email=em;
        status=st;


    }

    fun setJenis(pilihJK:String){
        _uiState.update { currentState -> currentState.copy(sex = pilihJK) }
    }
    fun setStatus(pilihST:String){
        _uiState.update { currentState -> currentState.copy(stat = pilihST) }
    }

}