package com.example.ordermanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ordermanager.ui.viewmodel.ClientViewModel
import com.example.ordermanager.ui.viewmodel.OrderViewModel
import com.example.ordermanager.ui.viewmodel.ProductViewModel

@Composable
fun OrderScreen(
    orderViewModel: OrderViewModel,
    clientViewModel: ClientViewModel,
    productViewModel: ProductViewModel,
    onBackClick: () -> Unit
) {
    val orders by orderViewModel.orders.collectAsState()
    val clients by clientViewModel.clients.collectAsState()
    val products by productViewModel.products.collectAsState()

    var selectedClientId by remember { mutableStateOf<Int?>(null) }
    var selectedProductId by remember { mutableStateOf<Int?>(null) }
    var quantity by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }

    val selectedProduct = products.find { it.id == selectedProductId }
    val totalValue = (quantity.toIntOrNull() ?: 0) * (selectedProduct?.price ?: 0.0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextButton(onClick = onBackClick) {
            Text("← Voltar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Pedidos",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Cadastre e acompanhe os pedidos",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Cliente")

                clients.forEach { client ->
                    Row {
                        RadioButton(
                            selected = selectedClientId == client.id,
                            onClick = { selectedClientId = client.id }
                        )
                        Text(client.name)
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text("Produto")

                products.forEach { product ->
                    Row {
                        RadioButton(
                            selected = selectedProductId == product.id,
                            onClick = { selectedProductId = product.id }
                        )
                        Text("${product.name} - R$ ${product.price}")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = quantity,
                    onValueChange = { newValue ->
                        quantity = newValue.filter { it.isDigit() }
                    },
                    label = { Text("Quantidade") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Valor total: R$ $totalValue",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (selectedClientId == null || selectedProductId == null || quantity.isBlank()) {
                            message = "Selecione cliente, produto e quantidade"
                            return@Button
                        }

                        orderViewModel.insert(
                            clientId = selectedClientId!!,
                            productId = selectedProductId!!,
                            quantity = quantity.toInt(),
                            orderDate = "19/06/2026",
                            orderTime = "12:00",
                            totalValue = totalValue
                        )

                        message = "Pedido cadastrado com sucesso"

                        selectedClientId = null
                        selectedProductId = null
                        quantity = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Salvar Pedido")
                }
            }
        }

        message?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = it,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Lista de Pedidos",
            style = MaterialTheme.typography.titleMedium
        )

        LazyColumn {
            items(orders) { order ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp),
                    elevation = CardDefaults.cardElevation(3.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text("Cliente: ${order.clientName}")
                        Text("Produto: ${order.productName}")
                        Text("Quantidade: ${order.quantity}")
                        Text("Data: ${order.orderDate}")
                        Text("Hora: ${order.orderTime}")
                        Text("Total: R$ ${order.totalValue}")
                    }
                }
            }
        }
    }
}