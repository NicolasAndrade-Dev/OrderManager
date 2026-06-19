package com.example.ordermanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ordermanager.data.entity.OrderWithDetails
import com.example.ordermanager.ui.viewmodel.ClientViewModel
import com.example.ordermanager.ui.viewmodel.OrderViewModel
import com.example.ordermanager.ui.viewmodel.ProductViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
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

    var orderToDelete by remember { mutableStateOf<OrderWithDetails?>(null) }

    var selectedClientId by remember { mutableStateOf<Int?>(null) }
    var selectedProductId by remember { mutableStateOf<Int?>(null) }
    var quantity by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf("Selecionar data") }
    var selectedTime by remember { mutableStateOf("Selecionar hora") }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    var message by remember { mutableStateOf<String?>(null) }

    val selectedProduct = products.find { it.id == selectedProductId }
    val totalValue = (quantity.toIntOrNull() ?: 0) * (selectedProduct?.price ?: 0.0)

    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

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

                Button(
                    onClick = { showDatePicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedDate)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { showTimePicker = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(selectedTime)
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Valor total: R$ $totalValue",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (
                            selectedClientId == null ||
                            selectedProductId == null ||
                            quantity.isBlank() ||
                            selectedDate == "Selecionar data" ||
                            selectedTime == "Selecionar hora"
                        ) {
                            message = "Preencha todos os dados do pedido"
                            return@Button
                        }

                        orderViewModel.insert(
                            clientId = selectedClientId!!,
                            productId = selectedProductId!!,
                            quantity = quantity.toInt(),
                            orderDate = selectedDate,
                            orderTime = selectedTime,
                            totalValue = totalValue
                        )

                        message = "Pedido cadastrado com sucesso"

                        selectedClientId = null
                        selectedProductId = null
                        quantity = ""
                        selectedDate = "Selecionar data"
                        selectedTime = "Selecionar hora"
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

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                orderToDelete = order
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDatePicker = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val millis = datePickerState.selectedDateMillis

                        if (millis != null) {
                            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            selectedDate = formatter.format(Date(millis))
                        }

                        showDatePicker = false
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        AlertDialog(
            onDismissRequest = {
                showTimePicker = false
            },
            title = {
                Text("Selecionar hora")
            },
            text = {
                TimePicker(state = timePickerState)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedTime = "%02d:%02d".format(
                            timePickerState.hour,
                            timePickerState.minute
                        )
                        showTimePicker = false
                    }
                ) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showTimePicker = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }

    orderToDelete?.let { order ->
        AlertDialog(
            onDismissRequest = {
                orderToDelete = null
            },
            title = {
                Text("Confirmar exclusão")
            },
            text = {
                Text("Deseja realmente excluir este pedido?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        orderViewModel.deleteById(order.id)
                        orderToDelete = null
                        message = "Pedido excluído"
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        orderToDelete = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}