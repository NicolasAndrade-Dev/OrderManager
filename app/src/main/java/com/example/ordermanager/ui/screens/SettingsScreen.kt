package com.example.ordermanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ordermanager.ui.viewmodel.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackClick: () -> Unit
) {

    val darkTheme by viewModel.darkTheme.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextButton(
            onClick = onBackClick
        ) {
            Text("← Voltar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Configurações",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Tema Escuro"
                    )

                    Switch(
                        checked = darkTheme,
                        onCheckedChange = {
                            viewModel.updateDarkTheme(it)
                        }
                    )
                }
            }
        }
    }
}