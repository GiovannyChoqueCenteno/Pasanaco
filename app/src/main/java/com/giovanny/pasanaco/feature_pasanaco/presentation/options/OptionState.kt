package com.giovanny.pasanaco.feature_pasanaco.presentation.options

import com.giovanny.pasanaco.feature_pasanaco.domain.model.Dia


data class OptionState(
    val diaActivo: Dia? = null,
    val showDialog: Boolean = false,
    val textDialog: String = "",
)