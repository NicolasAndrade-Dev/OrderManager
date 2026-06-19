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
    isAdmin: Boolean,
    onClientsClick: () -> Unit,
    onProductsClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onUsersClick: () -> Unit
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

        Text(
            text = "Resumo do Sistema",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        DashboardCard(
            title = "Clientes cadastrados",
            value = clientCount.toString(),
            icon = "👥"
        )

        DashboardCard(
            title = "Produtos cadastrados",
            value = productCount.toString(),
            icon = "📦"
        )

        DashboardCard(
            title = "Pedidos realizados",
            value = orderCount.toString(),
            icon = "🛒"
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

        Button(
            onClick = onOrdersClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pedidos")
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (isAdmin) {
            Button(
                onClick = onUsersClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Usuários / Vendedores")
            }

            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = onSettingsClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Configurações")
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    value: String,
    icon: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = value,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

            Text(
                text = icon,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}