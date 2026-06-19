package com.example.ordermanager

import androidx.compose.runtime.collectAsState
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ordermanager.data.database.DatabaseProvider
import com.example.ordermanager.data.repository.ClientRepository
import com.example.ordermanager.data.repository.ProductRepository
import com.example.ordermanager.data.repository.UserRepository
import com.example.ordermanager.ui.screens.ClientScreen
import com.example.ordermanager.ui.screens.HomeScreen
import com.example.ordermanager.ui.screens.LoginScreen
import com.example.ordermanager.ui.screens.ProductScreen
import com.example.ordermanager.ui.theme.OrderManagerTheme
import com.example.ordermanager.ui.viewmodel.ClientViewModel
import com.example.ordermanager.ui.viewmodel.ClientViewModelFactory
import com.example.ordermanager.ui.viewmodel.LoginViewModel
import com.example.ordermanager.ui.viewmodel.LoginViewModelFactory
import com.example.ordermanager.ui.viewmodel.ProductViewModel
import com.example.ordermanager.ui.viewmodel.ProductViewModelFactory
import com.example.ordermanager.data.repository.OrderRepository
import com.example.ordermanager.ui.screens.OrderScreen
import com.example.ordermanager.ui.viewmodel.OrderViewModel
import com.example.ordermanager.ui.viewmodel.OrderViewModelFactory
import com.example.ordermanager.data.datastore.SettingsDataStore
import com.example.ordermanager.ui.screens.SettingsScreen
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
            OrderManagerTheme {

                val loginViewModel: LoginViewModel = viewModel(
                    factory = LoginViewModelFactory(userRepository)
                )

                val settingsViewModel: SettingsViewModel = viewModel(
                    factory = SettingsViewModelFactory(settingsDataStore)
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

                var isLoggedIn by remember { mutableStateOf(false) }
                var currentScreen by remember { mutableStateOf("home") }

                if (isLoggedIn) {
                    when (currentScreen) {
                        "home" -> HomeScreen(
                            clientCount = clientViewModel.clients.collectAsState().value.size,
                            productCount = productViewModel.products.collectAsState().value.size,
                            orderCount = orderViewModel.orders.collectAsState().value.size,

                            onClientsClick = {
                                currentScreen = "clients"
                            },

                            onProductsClick = {
                                currentScreen = "products"
                            },

                            onOrdersClick = {
                                currentScreen = "orders"
                            },

                            onSettingsClick = {
                                currentScreen = "settings"
                            }

                        )
                        "settings" -> SettingsScreen(
                            viewModel = settingsViewModel,
                            onBackClick = {
                                currentScreen = "home"
                            }
                        )


                        "clients" -> ClientScreen(
                            viewModel = clientViewModel,
                            onBackClick = {
                                currentScreen = "home"
                            }
                        )

                        "products" -> ProductScreen(
                            viewModel = productViewModel,
                            onBackClick = {
                                currentScreen = "home"
                            }
                        )
                        "orders" -> OrderScreen(
                            orderViewModel = orderViewModel,
                            clientViewModel = clientViewModel,
                            productViewModel = productViewModel,
                            onBackClick = {
                                currentScreen = "home"
                            }
                        )
                    }
                } else {
                    LoginScreen(
                        viewModel = loginViewModel,
                        onLoginSuccess = {
                            isLoggedIn = true
                        }
                    )
                }
            }
        }
    }
}