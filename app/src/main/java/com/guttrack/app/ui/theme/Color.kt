package com.guttrack.app.ui.theme

import androidx.compose.ui.graphics.Color

val GtPrimary = Color(0xFF4355DB)
val GtPrimaryDark = Color(0xFF2E4FD1)
val GtAccent = Color(0xFF4C5FD5)
val GtBackground = Color(0xFFF1F5FE)
val GtSurface = Color(0xFFFFFFFF)
val GtChipBg = Color(0xFFE3EAFC)
val GtOnSurface = Color(0xFF1C1B1F)
val GtOnSurfaceDim = Color(0x731C1B1F)
val GtOnSurfaceFaint = Color(0x401C1B1F)

val SeverityColors = listOf(
    Color(0xFF4A7CFE),
    Color(0xFF7C6FE0),
    Color(0xFF5468D4),
    Color(0xFFD9668F),
    Color(0xFFF2495A),
)

fun severityColor(severity: Int): Color = SeverityColors[(severity.coerceIn(1, 5)) - 1]
