package com.example.mycertificate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycertificate.ui.theme.MyCertificateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCertificateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Certificate(
                        name = "Diego Alexei Escamilla García",
                        number = 90,
                        course = "Desarrollo de un proyecto audiovisual",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Certificate(name: String, modifier: Modifier = Modifier, number: Int, course: String) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Primer renglón escudos y nombre de la empresa
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Imagen Izquierda",
                modifier = Modifier.size(50.dp, 50.dp)
            )

            Text(
                text = "Alexei Digital Arts Center",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Imagen derecha",
                modifier = Modifier.size(50.dp, 50.dp)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Segundo renglón
        Text(
            text = "El presente se otorga a:",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        // Tercer renglón: imagen de fondo y nombre
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.stage5l2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit,
                alpha = 0.15f
            )

            Text(
                text = name,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        // Cuarto renglón
        Text(
            text = "Ha completado un total de $number horas mezclando.",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        // Quinto renglón
        Text(
            text = course,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Sexto renglón: representantes y firmas
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Representativo(
                signature = "____________________",
                name = "Alexed28",
                position = "Director Artista"
            )

            Representativo(
                signature = "____________________",
                name = "Sin William",
                position = "Instructor de dolor."
            )
        }
    }
}

@Composable
fun Representativo(
    signature: String,
    name: String,
    position: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = signature,
            textAlign = TextAlign.Center
        )

        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = position,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun CertificatePreview() {
    MyCertificateTheme {
        Certificate(
            name = "Diego Alexei Escamilla García",
            number = 2,
            course = "Creación de un album demencial"
        )
    }
}