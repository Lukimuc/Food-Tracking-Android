package com.guttrack.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.guttrack.app.R
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary

@Composable
fun SectionLabel(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text.uppercase(),
        modifier = modifier,
        fontSize = 10.5.sp,
        letterSpacing = 1.sp,
        fontWeight = FontWeight.SemiBold,
        color = GtOnSurfaceFaint,
    )
}

@Composable
fun GtSwitch(checked: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        colors = SwitchDefaults.colors(
            checkedTrackColor = GtPrimary,
            checkedThumbColor = Color.White,
            uncheckedTrackColor = Color(0xFFE3DDF0),
            uncheckedThumbColor = Color.White,
            uncheckedBorderColor = Color.Transparent,
            checkedBorderColor = Color.Transparent,
        ),
    )
}

@Composable
fun SeverityBadge(severity: Int, color: Color, modifier: Modifier = Modifier, small: Boolean = false) {
    Box(
        modifier = modifier
            .background(color, RoundedCornerShape(999.dp))
            .padding(horizontal = if (small) 8.dp else 10.dp, vertical = if (small) 2.dp else 3.dp),
    ) {
        Text(
            "Severity $severity/5",
            color = Color.White,
            fontSize = if (small) 10.sp else 11.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    initialHour: Int,
    initialMinute: Int,
    onDismiss: () -> Unit,
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    val state: TimePickerState = rememberTimePickerState(initialHour = initialHour, initialMinute = initialMinute, is24Hour = false)
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(28.dp))
                .padding(20.dp),
        ) {
            TimePicker(state = state)
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.End,
            ) {
                TextButton(onClick = onDismiss) { Text(stringResource(R.string.btn_cancel)) }
                TextButton(onClick = { onConfirm(state.hour, state.minute) }) { Text(stringResource(R.string.btn_ok)) }
            }
        }
    }
}

fun formatTime24to12(hhmm: String): String {
    val parts = hhmm.split(":")
    val hour = parts.getOrNull(0)?.toIntOrNull() ?: 0
    val minute = parts.getOrNull(1)?.toIntOrNull() ?: 0
    val ampm = if (hour >= 12) "PM" else "AM"
    var h12 = hour % 12
    if (h12 == 0) h12 = 12
    return "$h12:${minute.toString().padStart(2, '0')} $ampm"
}
