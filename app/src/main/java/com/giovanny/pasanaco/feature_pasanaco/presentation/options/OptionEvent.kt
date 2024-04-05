package com.giovanny.pasanaco.feature_pasanaco.presentation.options

sealed class OptionEvent {
    data object OpenDia : OptionEvent()
    data object ToggleDialog : OptionEvent()
    data object NewPasanaco : OptionEvent()
    data object ClearPasanaco : OptionEvent()
}