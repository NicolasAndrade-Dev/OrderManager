package com.example.ordermanager.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ordermanager.data.entity.ClientEntity

@Composable
fun ClientItem(
    client: ClientEntity,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(client.name)

            Text(client.phone)

            Text(client.email)

            Text(client.city)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onDelete
            ) {
                Text("Excluir")
            }
        }
    }
}