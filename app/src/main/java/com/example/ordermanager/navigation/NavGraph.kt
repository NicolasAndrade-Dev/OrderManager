package com.example.ordermanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ordermanager.ui.screens.ClientScreen
import com.example.ordermanager.ui.screens.HomeScreen
import com.example.ordermanager.ui.screens.LoginScreen
import com.example.ordermanager.ui.screens.OrderScreen
import com.example.ordermanager.ui.screens.ProductScreen
import com.example.ordermanager.ui.screens.SettingsScreen
import com.example.ordermanager.ui.viewmodel.ClientViewModel
import com.example.ordermanager.ui.viewmodel.LoginViewModel
import com.example.ordermanager.ui.viewmodel.OrderViewModel
import com.example.ordermanager.ui.viewmodel.ProductViewModel
import com.example.ordermanager.ui.viewmodel.SettingsViewModel

@Composable
fun NavGraph(
    loginViewModel: LoginViewModel,
    clientViewModel: ClientViewModel,
    productViewModel: ProductViewModel,
    orderViewModel: OrderViewModel,
    settingsViewModel: SettingsViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            val clients by clientViewModel.clients.collectAsState()
            val products by productViewModel.products.collectAsState()
            val orders by orderViewModel.orders.collectAsState()

            HomeScreen(
                clientCount = clients.size,
                productCount = products.size,
                orderCount = orders.size,
                onClientsClick = {
                    navController.navigate(Routes.CLIENTS)
                },
                onProductsClick = {
                    navController.navigate(Routes.PRODUCTS)
                },
                onOrdersClick = {
                    navController.navigate(Routes.ORDERS)
                },
                onSettingsClick = {
                    navController.navigate(Routes.SETTINGS)
                }
            )
        }

        composable(Routes.CLIENTS) {
            ClientScreen(
                viewModel = clientViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.PRODUCTS) {
            ProductScreen(
                viewModel = productViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.ORDERS) {
            OrderScreen(
                orderViewModel = orderViewModel,
                clientViewModel = clientViewModel,
                productViewModel = productViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Routes.SETTINGS) {
            SettingsScreen(
                viewModel = settingsViewModel,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}