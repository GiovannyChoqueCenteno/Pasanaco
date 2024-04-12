package com.giovanny.pasanaco.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.giovanny.pasanaco.R


val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
val fontName = GoogleFont("Roboto")
val fontFamily = FontFamily(
    Font(
        googleFont = fontName,
        fontProvider = provider,
        weight = FontWeight.Normal,
        style = FontStyle.Normal
    )
)
private val defaultTypography = Typography()

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = fontFamily),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = fontFamily),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = fontFamily),

    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = fontFamily),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = fontFamily),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = fontFamily),

    titleLarge = defaultTypography.titleLarge.copy(fontFamily = fontFamily),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = fontFamily),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = fontFamily),

    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = fontFamily),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = fontFamily),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = fontFamily),

    labelLarge = defaultTypography.labelLarge.copy(fontFamily = fontFamily),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = fontFamily),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = fontFamily)
)