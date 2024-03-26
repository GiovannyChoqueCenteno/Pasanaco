package com.giovanny.pasanaco.core

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.RowScope

data class AppBarState(
    val title: String = "",
    val actions: (@Composable RowScope.() -> Unit)? = null,
    val floattingActionButton: (@Composable () -> Unit)? = null,
    val isTabItem: Boolean = false
)