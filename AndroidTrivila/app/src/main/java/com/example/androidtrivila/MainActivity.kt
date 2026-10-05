import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    KonversiAir()
                }
            }
        }
    }
}

@Composable
fun KonversiAir() {

    var volume by remember { mutableStateOf("") }
    var satuan by remember { mutableStateOf("mL") }
    var hasil by remember { mutableStateOf<Double?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Konversi Volume Air",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Menghitung massa air berdasarkan volumenya",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(28.dp))

        OutlinedTextField(
            value = volume,
            onValueChange = {
                volume = it
                hasil = null
            },
            label = {
                Text("Masukkan volume air")
            },
            placeholder = {
                Text("Contoh: 500")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Satuan volume: $satuan",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))

        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    satuan = "mL"
                    hasil = null
                }
            ) {
                Text("mL")
            }

            Button(
                onClick = {
                    satuan = "L"
                    hasil = null
                }
            ) {
                Text("Liter")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val nilai = volume.toDoubleOrNull()

                hasil = if (nilai != null && nilai >= 0) {
                    if (satuan == "mL") {
                        nilai
                    } else {
                        nilai * 1000.0
                    }
                } else {
                    null
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Konversi ke Massa Air")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Hasil Konversi",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                if (hasil != null) {

                    Text(
                        text = String.format(
                            Locale.US,
                            "%.2f gram",
                            hasil!!
                        ),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = String.format(
                            Locale.US,
                            "%.3f kilogram",
                            hasil!! / 1000.0
                        ),
                        fontSize = 16.sp
                    )

                } else {

                    Text(
                        text = "Masukkan volume, lalu tekan tombol konversi.",
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Rumus: Massa = Volume × Massa Jenis",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Massa jenis air ≈ 1 gram/mL",
            fontSize = 14.sp
        )
    }
}