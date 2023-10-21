package com.example.pertemuan4

import android.os.Bundle
import android.provider.SyncStateContract.Columns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan4.Data.DataForm
import com.example.pertemuan4.ui.theme.Pertemuan4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pertemuan4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TampilLayar()
                }
            }
        }
    }
}

@Composable
fun TampilLayar(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment =Alignment.CenterHorizontally
    ) {
        TampilText()



    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TampilText(cobaViewModel: CobaViewModel = viewModel()) {
    var textNama by remember {
        mutableStateOf("")
    }
    var texttlp by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val dataForm : DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState;

    Column() {
        OutlinedTextField(
            value = textNama,
            onValueChange ={textNama = it},
            label = { Text(text = "Nama Lengkap")},
            modifier = Modifier.padding(10.dp),
            placeholder = { Text(text = "Nadiv Nugraha")}
        )
        OutlinedTextField(
            value = texttlp,
            onValueChange ={texttlp = it},
            label = { Text(text = "Nomor Telepon")},
            modifier = Modifier.padding(10.dp)
        )

        Button(onClick = { /*TODO*/ }) {

        }

        TextHasil(namanya = "Andi Harun", telponya ="084973846" , jenisnya ="Bencong" )

    }
}

@Composable
fun Jenisbox(
    option: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){
    var selectedValue by rememberSaveable {
        mutableStateOf("")

    }
    Column(modifier = Modifier.padding(16.dp)) {
        option.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedValue == item,
                    onClick = {
                        selectedValue = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedValue == item,
                    onClick = { selectedValue = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)

            }
        }

    }
}

@Composable
fun TextHasil(namanya: String, telponya: String, jenisnya:String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.size(width = 300.dp, height = 100.dp)
    ) {
        Text(text = "Nama : " + namanya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(text = "Telepon : " + telponya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Pertemuan4Theme {
        TampilLayar()
    }
}