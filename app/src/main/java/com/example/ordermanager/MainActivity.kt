package com.example.ordermanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ordermanager.data.database.DatabaseProvider
import com.example.ordermanager.data.datastore.SettingsDataStore
import com.example.ordermanager.data.repository.ClientRepository
import com.example.ordermanager.data.repository.OrderRepository
import com.example.ordermanager.data.repository.ProductRepository
import com.example.ordermanager.data.repository.UserRepository
import com.example.ordermanager.navigation.NavGraph
import com.example.ordermanager.ui.theme.OrderManagerTheme
import com.example.ordermanager.ui.viewmodel.ClientViewModel
import com.example.ordermanager.ui.viewmodel.ClientViewModelFactory
import com.example.ordermanager.ui.viewmodel.LoginViewModel
import com.example.ordermanager.ui.viewmodel.LoginViewModelFactory
import com.example.ordermanager.ui.viewmodel.OrderViewModel
import com.example.ordermanager.ui.viewmodel.OrderViewModelFactory
import com.example.ordermanager.ui.viewmodel.ProductViewModel
import com.example.ordermanager.ui.viewmodel.ProductViewModelFactory
import com.example.ordermanager.ui.viewmodel.SettingsViewModel
import com.example.ordermanager.ui.viewmodel.SettingsViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(this)

        val userRepository = UserRepository(database.userDao())
        val clientRepository = ClientRepository(database.clientDao())
        val productRepository = ProductRepository(database.productDao())
        val orderRepository = OrderRepository(database.orderDao())
        val settingsDataStore = SettingsDataStore(this)

        setContent {

            val settingsViewModel: SettingsViewModel = viewModel(
                factory = SettingsViewModelFactory(settingsDataStore)
            )

            val darkTheme by settingsViewModel.darkTheme.collectAsState()

            OrderManagerTheme(
                darkTheme = darkTheme,
                dynamicColor = false
            ) {
                val loginViewModel: LoginViewModel = viewModel(
                    factory = LoginViewModelFactory(userRepository)
                )

                val clientViewModel: ClientViewModel = viewModel(
                    factory = ClientViewModelFactory(clientRepository)
                )

                val productViewModel: ProductViewModel = viewModel(
                    factory = ProductViewModelFactory(productRepository)
                )

                val orderViewModel: OrderViewModel = viewModel(
                    factory = OrderViewModelFactory(orderRepository)
                )

                NavGraph(
                    loginViewModel = loginViewModel,
                    clientViewModel = clientViewModel,
                    productViewModel = productViewModel,
                    orderViewModel = orderViewModel,
                    settingsViewModel = settingsViewModel
                )
            }
        }
    }
}