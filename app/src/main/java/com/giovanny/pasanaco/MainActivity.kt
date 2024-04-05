package com.giovanny.pasanaco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.giovanny.pasanaco.core.AppBarState
import com.giovanny.pasanaco.feature_pasanaco.presentation.home.HomeScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.new_pago.NewPagoScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.new_participante.NewParticipanteScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.options.OptionScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list.PagoListScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.pagos_participante.PagosParticipanteScreen
import com.giovanny.pasanaco.feature_pasanaco.presentation.participante_list.ParticipanteListScreen
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
                        screen = Screen.PagoNavigation
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
                            CenterAlignedTopAppBar(
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

                                    Text(text = appBarState.title)
                                })
                        },
                        floatingActionButton = {
                            appBarState.floattingActionButton?.invoke()
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
                            navigation(
                                startDestination = Screen.Pagos.route,
                                route = Screen.PagoNavigation.route
                            ) {
                                composable(Screen.Pagos.route) {
                                    PagoListScreen(
                                        onComposing = {
                                            appBarState = it
                                        }, navController = navController,
                                        snackbarHostState = snackbarHostState,
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
                                        snackbarHostState = snackbarHostState,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(
                                                bottom = padding.calculateBottomPadding(),
                                                top = padding.calculateTopPadding()
                                            )
                                    )
                                }
                                navigation(
                                    startDestination = Screen.Opciones.route,
                                    route = Screen.OpcionesNavigation.route
                                ) {
                                    composable(Screen.Opciones.route) {
                                        OptionScreen(
                                            onComposing = {
                                                appBarState = it
                                            },
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(
                                                    bottom = padding.calculateBottomPadding(),
                                                    top = padding.calculateTopPadding()
                                                ),
                                            navController = navController
                                        )
                                    }
                                    composable(Screen.Participantes.route) {
                                        ParticipanteListScreen(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = padding.calculateTopPadding()),
                                            onComposing = {
                                                appBarState = it
                                            },
                                            navController = navController
                                        )
                                    }
                                    composable(Screen.NuevoParticipante.route) {
                                        NewParticipanteScreen(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = padding.calculateTopPadding()),
                                            onComposing = {
                                                appBarState = it
                                            },
                                            navController = navController,
                                            snackbarHostState = snackbarHostState
                                        )

                                    }
                                    composable(Screen.PagoParticipante.route) {
                                        PagosParticipanteScreen(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(top = padding.calculateTopPadding()),
                                            onComposing = {
                                                appBarState = it
                                            },
                                        )
                                    }
                                }
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
    data object Home : Screen("home")
    data object Pagos : Screen("pagos")
    data object NuevoPago : Screen("nuevopago")
    data object Opciones : Screen("opciones")
    data object Participantes : Screen("participantes")
    data object NuevoParticipante : Screen("nuevoparticipante")
    data object PagoParticipante : Screen("pagoParticipante")

    data object PagoNavigation : Screen("pagonavigation")
    data object OpcionesNavigation : Screen("opcionesnavigation")

}

