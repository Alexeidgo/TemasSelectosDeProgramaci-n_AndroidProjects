package com.mexiti.costogasolina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mexiti.costogasolina.ui.theme.CostoGasolinaTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CostoGasolinaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.LightGray
                ) {
                    CostGasLayout()
                }
            }
        }
    }
}

@Composable
fun CostGasLayout() {

    var precioLitroEntrada by remember {
        mutableStateOf("")
    }

    var cantLitrosEntrada by remember {
        mutableStateOf("")
    }

    var propinaEntrada by remember {
        mutableStateOf("")
    }

    // Guarda si el Switch está encendido o apagado
    var agregarPropina by remember {
        mutableStateOf(false)
    }

    val precioLitro = precioLitroEntrada.toDoubleOrNull() ?: 0.0
    val cantidadLitros = cantLitrosEntrada.toDoubleOrNull() ?: 0.0
    val propina = propinaEntrada.toDoubleOrNull() ?: 0.0

    val total = calcularMonto(
        precio = precioLitro,
        cantidadLitros = cantidadLitros,
        propina = propina,
        agregarPropina = agregarPropina
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Calcular monto de gasolina",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        EditNumberField(
            label = R.string.ingresa_gasolina,
            leadingIcon = R.drawable.money_gas,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            value = precioLitroEntrada,
            onValueChanged = {
                precioLitroEntrada = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        EditNumberField(
            label = R.string.ingresa_la_cantidad_de_litros,
            leadingIcon = R.drawable.gasolina,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            value = cantLitrosEntrada,
            onValueChanged = {
                cantLitrosEntrada = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        EditNumberField(
            label = R.string.ingresa_la_propina_que_deseas_dejar,
            leadingIcon = R.drawable.baseline_emoji_people_24,
            keyboardsOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            value = propinaEntrada,
            onValueChanged = {
                propinaEntrada = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "¿Deseas agregar la propina?",
                fontSize = 16.sp
            )

            Switch(
                checked = agregarPropina,
                onCheckedChange = {
                    agregarPropina = it
                }
            )
        }

        Text(
            text = "Monto total: $total",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 8.dp)
        )
    }
}

@Composable
fun EditNumberField(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    keyboardsOptions: KeyboardOptions,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        label = {
            Text(text = stringResource(id = label))
        },
        value = value,
        onValueChange = onValueChanged,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = null
            )
        },
        keyboardOptions = keyboardsOptions,
        modifier = modifier
    )
}

private fun calcularMonto(
    precio: Double,
    cantidadLitros: Double,
    propina: Double,
    agregarPropina: Boolean
): String {

    val costoGasolina = precio * cantidadLitros

    val montoTotal = if (agregarPropina) {
        costoGasolina + propina
    } else {
        costoGasolina
    }

    return NumberFormat
        .getCurrencyInstance()
        .format(montoTotal)
}

@Preview(showBackground = true)
@Composable
fun CostGasLayoutPreview() {
    CostoGasolinaTheme {
        CostGasLayout()
    }
}