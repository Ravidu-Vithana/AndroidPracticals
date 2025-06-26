package com.ryvk.dependancyinjection

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryvk.dependancyinjection.daggerhilt.Vehicle
import com.ryvk.dependancyinjection.manualinjection.Engine1
import com.ryvk.dependancyinjection.manualinjection.Engine2
import com.ryvk.dependancyinjection.manualinjection.HiLux
import com.ryvk.dependancyinjection.manualinjection.LandCruiser
import com.ryvk.dependancyinjection.ui.theme.DependancyInjectionTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject @Named("HiLux") lateinit var hiLux: Vehicle
    @Inject @Named("LandCruiser") lateinit var landCruiser: Vehicle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DependancyInjectionTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Ui(
                        innerPadding,
                        startManual = {
                            startManualDependencyInjectionProcess()
                        },
                        startHilt = {
                            startHiltDependencyInjectionProcess()
                        }
                    )
                }
            }
        }
    }

    fun startManualDependencyInjectionProcess(){
        val engine1 = Engine1("V6", 500, 400)
        val hiLux = HiLux(engine1)
        Log.d("DI", "ManualDependencyInjection - HiLux: ${hiLux.startEngine()}")
        val engine2 = Engine2("V8", 600, 500)
        val landCruiser = LandCruiser(engine2)
        Log.d("DI", "ManualDependencyInjection - LandCruiser: ${landCruiser.startEngine()}")
    }

    fun startHiltDependencyInjectionProcess(){
        Log.d("DI", "HiltDependencyInjection - HiLux: ${hiLux.startEngine()}")
        Log.d("DI", "HiltDependencyInjection - HiLux: ${landCruiser.startEngine()}")
    }

}

@Preview(showBackground = true)
@Composable
fun Ui(
    innerPaddingValues: PaddingValues = PaddingValues(0.dp),
    startManual: () -> Unit = {},
    startHilt: () -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPaddingValues)
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Dependency Injection Practical. Refer the code."
        )
        Button(onClick = {
            startManual()
        }) {
            Text("Check Manual Dependency Injection")
        }
        Button(onClick = {
            startHilt()
        }) {
            Text("Check Hilt Dependency Injection")
        }
    }
}