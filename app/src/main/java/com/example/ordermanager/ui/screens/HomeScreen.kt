package com.example.ordermanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    clientCount: Int,
    productCount: Int,
    orderCount: Int,
    onClientsClick: () -> Unit,
    onProductsClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "OrderManager",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Controle de Pedidos e Clientes",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Resumo do Sistema",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("👥 Clientes cadastrados: $clientCount")
                Text("📦 Produtos cadastrados: $productCount")
                Text("🛒 Pedidos realizados: $orderCount")
            }
        }

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

        Button(
            onClick = onOrdersClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pedidos")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Configurações")
        }
    }

}
