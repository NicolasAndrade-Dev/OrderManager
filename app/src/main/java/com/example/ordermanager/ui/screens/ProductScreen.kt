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
import com.example.ordermanager.data.entity.ProductEntity
import com.example.ordermanager.ui.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
    onBackClick: () -> Unit
) {

    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf("") }

    var editingProduct by remember { mutableStateOf<ProductEntity?>(null) }
    var productToDelete by remember { mutableStateOf<ProductEntity?>(null) }

    var message by remember { mutableStateOf<String?>(null) }

    val products by viewModel.products.collectAsState()

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
            text = "Produtos",
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Gerencie os produtos cadastrados",
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
                    label = { Text("Nome do Produto") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = price,
                    onValueChange = { newValue ->
                        price = newValue.filter { it.isDigit() || it == '.' || it == ',' }
                    },
                    label = { Text("Preço") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = stock,
                    onValueChange = { newValue ->
                        stock = newValue.filter { it.isDigit() }
                    },
                    label = { Text("Quantidade em Estoque") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        if (
                            name.isBlank() ||
                            description.isBlank() ||
                            price.isBlank() ||
                            stock.isBlank()
                        ) {
                            message = "Preencha todos os campos"
                            return@Button
                        }

                        val formattedPrice = price.replace(",", ".")
                        val productPrice = formattedPrice.toDoubleOrNull()
                        val productStock = stock.toIntOrNull()

                        if (productPrice == null || productPrice <= 0.0) {
                            message = "Digite um preço válido"
                            return@Button
                        }

                        if (productStock == null || productStock < 0) {
                            message = "Digite um estoque válido"
                            return@Button
                        }

                        if (editingProduct == null) {
                            viewModel.insert(
                                name,
                                description,
                                productPrice,
                                productStock
                            )

                            message = "Produto cadastrado com sucesso"
                        } else {
                            viewModel.update(
                                editingProduct!!.copy(
                                    name = name,
                                    description = description,
                                    price = productPrice,
                                    stockQuantity = productStock
                                )
                            )

                            message = "Produto atualizado com sucesso"

                            editingProduct = null
                        }

                        name = ""
                        description = ""
                        price = ""
                        stock = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        if (editingProduct == null)
                            "Salvar Produto"
                        else
                            "Atualizar Produto"
                    )
                }

                if (editingProduct != null) {
                    TextButton(
                        onClick = {
                            editingProduct = null

                            name = ""
                            description = ""
                            price = ""
                            stock = ""
                        }
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
            text = "Lista de Produtos",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onEdit = {
                        editingProduct = product

                        name = product.name
                        description = product.description
                        price = product.price.toString()
                        stock = product.stockQuantity.toString()
                    },
                    onDelete = {
                        productToDelete = product
                    }
                )
            }
        }
    }

    productToDelete?.let { product ->
        AlertDialog(
            onDismissRequest = {
                productToDelete = null
            },
            title = {
                Text("Confirmar exclusão")
            },
            text = {
                Text("Deseja realmente excluir o produto ${product.name}?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.delete(product)
                        productToDelete = null
                        message = "Produto excluído"
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        productToDelete = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun ProductCard(
    product: ProductEntity,
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
                text = product.name,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Descrição: ${product.description}")
            Text("Preço: R$ ${product.price}")
            Text("Estoque: ${product.stockQuantity}")

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