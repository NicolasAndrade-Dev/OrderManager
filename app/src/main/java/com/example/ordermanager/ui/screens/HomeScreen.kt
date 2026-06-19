package com.example.ordermanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    onClientsClick: () -> Unit,
    onProductsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo ao OrderManager",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onClientsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clientes")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onProductsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Produtos")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("Pedidos")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("Configurações")
        }
    }
}