package com.guttrack.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

private val GutTrackColorScheme = lightColorScheme(
    primary = GtPrimary,
    onPrimary = Color.White,
    primaryContainer = GtChipBg,
    onPrimaryContainer = GtPrimaryDark,
    background = GtBackground,
    onBackground = GtOnSurface,
    surface = GtSurface,
    onSurface = GtOnSurface,
)

private val GutTrackTypography = Typography(
    bodyLarge = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
    bodyMedium = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.Normal),
    titleLarge = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    titleMedium = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Bold),
)

@Composable
fun GutTrackTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = GutTrackColorScheme,
        typography = GutTrackTypography,
        content = content,
    )
}
