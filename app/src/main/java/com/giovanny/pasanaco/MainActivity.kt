package com.giovanny.pasanaco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.giovanny.pasanaco.core.AppBarState
import com.giovanny.pasanaco.core.currentRouteAsState
import com.giovanny.pasanaco.feature_pasanaco.presentation.home.HomeScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago.NewPagoScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.options.OptionScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list.PagoListScreen
import com.giovanny.pasanaco.ui.theme.PasanacoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PasanacoTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                var appBarState by remember {
                    mutableStateOf(
                        AppBarState(
                            isTabItem = true,
                            title = "Home",
                        )
                    )
                }
                val items = listOf(
                    BottomNavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        screen = Screen.Home
                    ),
                    BottomNavigationItem(
                        title = "Pagos",
                        selectedIcon = Icons.Filled.CreditCard,
                        unselectedIcon = Icons.Outlined.CreditCard,
                        screen = Screen.Pagos
                    ),
                    BottomNavigationItem(
                        title = "Opciones",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        screen = Screen.Opciones
                    )
                )
                var selectedItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        topBar = {
                            TopAppBar(
                                navigationIcon = {
                                    if (!appBarState.isTabItem)
                                        IconButton(onClick = {
                                            navController.popBackStack()
                                        }) {
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                                contentDescription = "Back"
                                            )
                                        }

                                },
                                title = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(text = appBarState.title)
                                    }
                                })
                        },
                        floatingActionButton = {
                            appBarState.floattingActionButton?.invoke()
//                            Box() {
//                                if (currentRoute == null || bottomNavRoutes.contains(currentRoute)) {
//                                    FloatingActionButton(
//                                        shape = CircleShape,
//                                        modifier = Modifier
//                                            .align(Alignment.Center)
//                                            .size(60.dp)
//                                            .offset(y = 50.dp),
//                                        onClick = {
//                                            navController.navigate(Screen.NuevoPago.route)
//                                        }) {
//                                        Icon(
//                                            imageVector = Icons.Default.Add,
//                                            contentDescription = ""
//                                        )
//                                    }
//                                }
//                            }
                        },
                        bottomBar = {
                            if (appBarState.isTabItem) {
                                NavigationBar {
                                    items.forEachIndexed { index, item ->
                                        NavigationBarItem(
                                            label = {
                                                Text(text = item.title)
                                            },
                                            selected = index == selectedItemIndex,
                                            onClick = {
                                                selectedItemIndex = index
                                                navController.navigate(item.screen.route) {
                                                    popUpTo(0)
                                                }
                                            },
                                            icon = {
                                                Icon(
                                                    imageVector = if (index == selectedItemIndex)
                                                        item.selectedIcon
                                                    else
                                                        item.unselectedIcon,
                                                    contentDescription = item.title
                                                )
                                            })
                                    }
                                }
                            }
                        }
                    ) { padding ->
                        NavHost(navController, startDestination = Screen.Home.route) {
                            composable(Screen.Home.route) {
                                HomeScreen(
                                    onComposing = {
                                        appBarState = it
                                    },
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            bottom = padding.calculateBottomPadding(),
                                            top = padding.calculateTopPadding()
                                        )
                                )
                            }
                            composable(Screen.Pagos.route) {
                                PagoListScreen(
                                    onComposing = {
                                        appBarState = it
                                    }, navController = navController,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            bottom = padding.calculateBottomPadding(),
                                            top = padding.calculateTopPadding()
                                        )
                                )
                            }
                            composable(Screen.Opciones.route) {
                                OptionScreen(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            bottom = padding.calculateBottomPadding(),
                                            top = padding.calculateTopPadding()
                                        )
                                )
                            }
                            composable(Screen.NuevoPago.route) {
                                NewPagoScreen(
                                    navController = navController,
                                    onComposing = {
                                        appBarState = it
                                    },
                                    snackbarHostState  = snackbarHostState,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            bottom = padding.calculateBottomPadding(),
                                            top = padding.calculateTopPadding()
                                        )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector,
    val screen: Screen,
)

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Pagos : Screen("pagos")
    object NuevoPago : Screen("nuevopago")
    object Opciones : Screen("opciones")
}