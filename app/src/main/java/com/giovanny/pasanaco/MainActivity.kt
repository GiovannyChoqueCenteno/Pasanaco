package com.giovanny.pasanaco

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.giovanny.pasanaco.feature_pasanaco.presentation.pago_list.PagoListScreen
import com.giovanny.pasanaco.ui.theme.PasanacoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val tabItems = listOf<>(
//            BottomNavigationItem(
//                title = "Pagos",
//                selectedIcon =
//            )
//        )
        setContent {
            PasanacoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PagoListScreen()
                }
            }
        }
    }
}

//data class BottomNavigationItem(
//    val title: String,
//    val unselectedIcon: ImageVector,
//    val selectedIcon: ImageVector
//)