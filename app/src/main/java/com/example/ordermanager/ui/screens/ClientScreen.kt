package com.example.ordermanager.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ordermanager.data.entity.ClientEntity
import com.example.ordermanager.ui.viewmodel.ClientViewModel

@Composable
fun ClientScreen(
    viewModel: ClientViewModel,
    onBackClick: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }

    var editingClient by remember { mutableStateOf<ClientEntity?>(null) }
    var clientToDelete by remember { mutableStateOf<ClientEntity?>(null) }

    var message by remember { mutableStateOf<String?>(null) }

    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    val clients by viewModel.clients.collectAsState()

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
            text = "Clientes",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Gerencie seus clientes cadastrados",
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
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { newValue ->
                        phone = newValue.filter { it.isDigit() }
                    },
                    label = { Text("Telefone") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("E-mail") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = city,
                    onValueChange = { city = it },
                    label = { Text("Cidade") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (
                            name.isBlank() ||
                            phone.isBlank() ||
                            email.isBlank() ||
                            city.isBlank()
                        ) {
                            message = "Preencha todos os campos"
                            return@Button
                        }

                        if (!emailRegex.matches(email)) {
                            message = "Digite um e-mail válido"
                            return@Button
                        }

                        if (phone.length < 10 || phone.length > 11) {
                            message = "Telefone inválido"
                            return@Button
                        }

                        if (editingClient == null) {
                            viewModel.insert(name, phone, email, city)
                            message = "Cliente cadastrado com sucesso"
                        } else {
                            viewModel.update(
                                editingClient!!.copy(
                                    name = name,
                                    phone = phone,
                                    email = email,
                                    city = city
                                )
                            )
                            message = "Cliente atualizado com sucesso"
                            editingClient = null
                        }

                        name = ""
                        phone = ""
                        email = ""
                        city = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (editingClient == null)
                            "Salvar Cliente"
                        else
                            "Atualizar Cliente"
                    )
                }

                if (editingClient != null) {
                    TextButton(
                        onClick = {
                            editingClient = null
                            name = ""
                            phone = ""
                            email = ""
                            city = ""
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Cancelar edição")
                    }
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
            text = "Lista de Clientes",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(clients) { client ->
                ClientCard(
                    client = client,
                    onEdit = {
                        editingClient = client
                        name = client.name
                        phone = client.phone
                        email = client.email
                        city = client.city
                    },
                    onDelete = {
                        clientToDelete = client
                    }
                )
            }
        }
    }

    clientToDelete?.let { client ->
        AlertDialog(
            onDismissRequest = {
                clientToDelete = null
            },
            title = {
                Text("Confirmar exclusão")
            },
            text = {
                Text("Deseja realmente excluir o cliente ${client.name}?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.delete(client)
                        clientToDelete = null
                        message = "Cliente excluído"
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        clientToDelete = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun ClientCard(
    client: ClientEntity,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = client.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Telefone: ${client.phone}")
            Text("E-mail: ${client.email}")
            Text("Cidade: ${client.city}")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }

                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Excluir",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}