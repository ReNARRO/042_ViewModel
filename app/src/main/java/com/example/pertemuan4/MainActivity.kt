package com.example.pertemuan4

import android.os.Bundle
import android.provider.SyncStateContract.Columns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pertemuan4.Data.DataForm
import com.example.pertemuan4.Data.DataSource.jenis
import com.example.pertemuan4.Data.DataSource.status
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
    Card(
        modifier = Modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Column (
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ){
            TampilText()
        }

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
    var textEmail by remember {
        mutableStateOf("")
    }
    var textalamat by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val dataForm : DataForm
    val uiState by cobaViewModel.uiState.collectAsState()
    dataForm = uiState;


    Row(
        horizontalArrangement = Arrangement.spacedBy(120.dp),
        modifier = Modifier
            .fillMaxWidth()) {
        Image(painter = painterResource(R.drawable.arrow_back), contentDescription = " " )

        Text(text = "Register",
            fontSize = 15.sp
        )
    }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Create Your Account",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold

        )
    }


    Column {
        OutlinedTextField(
            value = textNama,
            onValueChange ={textNama = it},
            label = { Text(text = "Username")},
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "ReNARRO")}
        )
        OutlinedTextField(
            value = texttlp,
            onValueChange ={texttlp = it},
            label = { Text(text = "Telepon")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = textEmail,
            onValueChange ={textEmail = it},
            label = { Text(text = "Email")},
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )

        Jenisbox(
            option = jenis.map { id -> context.resources.getString(id) },
            onSelectionChanged = {cobaViewModel.setJenis(it)}
        )

        Jenisbox(
            option = status.map { id -> context.resources.getString(id)},
            onSelectionChanged = {cobaViewModel.setNikah(it)}
        )

        OutlinedTextField(
            value = textalamat,
            onValueChange ={textalamat = it},
            label = { Text(text = "Alamat")},
            singleLine = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )


        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { cobaViewModel.insertData(textNama,texttlp,textalamat,textEmail,dataForm.stat,dataForm.sex)}) {
            Text(
                text = stringResource(id = R.string.regis)
            )

        }
        Spacer(modifier = Modifier.height(100.dp))
        TextHasil(
            emailnya = cobaViewModel.email,
            jenisnya = cobaViewModel.jeniskl,
            alamatnya = cobaViewModel.alamat,
            statusnya = cobaViewModel.status,

        )
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
        Text(text = "Jenis Kelamin:")
        Row() {
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


}
@Composable
fun Menikah(
    option: List<String>,
    onSelectionChanged: (String) -> Unit = {}
){
    var selectedVal by rememberSaveable {
        mutableStateOf("")

    }
    Text(text = "Statusnya:")
    Row(modifier = Modifier.padding(16.dp)) {
        option.forEach { item ->
            Row (
                modifier = Modifier.selectable(
                    selected = selectedVal == item,
                    onClick = {
                        selectedVal = item
                        onSelectionChanged(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = selectedVal == item,
                    onClick = { selectedVal = item
                        onSelectionChanged(item)
                    }
                )
                Text(item)

            }
        }

    }
}

@Composable
fun TextHasil(emailnya: String,statusnya:String,alamatnya:String, jenisnya:String){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        modifier = Modifier.size(width = 300.dp, height = 130.dp)
    ) {
        Text(text = "Jenis Kelamin : " + jenisnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(text = "Status Menikah : " + statusnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(text = "Alamat : " + alamatnya,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 4.dp)
        )
        Text(text = "Email : " + emailnya,
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