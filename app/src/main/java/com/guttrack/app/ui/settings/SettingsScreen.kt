package com.guttrack.app.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guttrack.app.R
import com.guttrack.app.notif.ReminderScheduler
import com.guttrack.app.ui.components.GtSwitch
import com.guttrack.app.ui.components.SectionLabel
import com.guttrack.app.ui.components.TimePickerDialog
import com.guttrack.app.ui.components.formatTime24to12
import com.guttrack.app.ui.theme.GtChipBg
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.viewmodel.GutTrackViewModel
import java.time.LocalDate

@Composable
fun SettingsScreen(viewModel: GutTrackViewModel) {
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var openPicker by remember { mutableStateOf<String?>(null) }
    var exactAlarmGranted by remember { mutableStateOf(ReminderScheduler.canScheduleExact(context)) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) exactAlarmGranted = ReminderScheduler.canScheduleExact(context)
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    val daysElapsed = if (settings.trackingStartEpoch <= 0L) 0 else (LocalDate.now().toEpochDay() - settings.trackingStartEpoch).toInt().coerceAtLeast(0)
    val totalDays = settings.totalDays
    val daysLeft = (totalDays - daysElapsed).coerceAtLeast(0)
    val pct = if (totalDays > 0) (daysElapsed.toFloat() / totalDays).coerceIn(0f, 1f) else 0f

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {
        item {
            Text(stringResource(R.string.tab_settings), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp, bottom = 6.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(stringResource(R.string.settings_tracking_period), fontSize = 12.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.weight(1f))
                    Text(
                        stringResource(R.string.settings_day_count, daysElapsed, totalDays, daysLeft),
                        fontSize = 12.5.sp, fontWeight = FontWeight.Bold,
                        textAlign = androidx.compose.ui.text.style.TextAlign.End,
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .height(8.dp)
                        .background(GtChipBg, RoundedCornerShape(4.dp)),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(pct)
                            .fillMaxHeight()
                            .background(GtPrimary, RoundedCornerShape(4.dp)),
                    ) {}
                }
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(18.dp))
        }

        item {
            SectionLabel(stringResource(R.string.settings_meal_reminders), modifier = Modifier.padding(bottom = 10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp),
            ) {
                ReminderRow(stringResource(R.string.meal_breakfast), settings.reminderBreakfast, showDivider = true) { openPicker = "breakfast" }
                ReminderRow(stringResource(R.string.meal_lunch), settings.reminderLunch, showDivider = true) { openPicker = "lunch" }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(stringResource(R.string.settings_dinner_window), fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        TimeChip(settings.reminderDinnerStart) { openPicker = "dinnerStart" }
                        Text("–", color = GtOnSurfaceFaint)
                        TimeChip(settings.reminderDinnerEnd) { openPicker = "dinnerEnd" }
                    }
                }
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(20.dp))
        }

        item {
            SectionLabel(stringResource(R.string.settings_notifications), modifier = Modifier.padding(bottom = 10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleFollowUp(context) }
                        .padding(vertical = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(stringResource(R.string.settings_followup), fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
                        Text(stringResource(R.string.settings_followup_sub), fontSize = 11.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 2.dp))
                    }
                    GtSwitch(checked = settings.followUpEnabled, onCheckedChange = { viewModel.toggleFollowUp(context) })
                }
                Divider(color = GtChipBg)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.toggleSnackAsk(context) }
                        .padding(vertical = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(stringResource(R.string.settings_snackask), fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
                        Text(stringResource(R.string.settings_snackask_sub), fontSize = 11.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 2.dp))
                    }
                    GtSwitch(checked = settings.snackAskEnabled, onCheckedChange = { viewModel.toggleSnackAsk(context) })
                }
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(20.dp))
        }

        item {
            SectionLabel(stringResource(R.string.settings_app_settings), modifier = Modifier.padding(bottom = 10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 13.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(stringResource(R.string.settings_language), fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        LanguageChip("English", active = settings.language == "en") { viewModel.setLanguage("en") }
                        LanguageChip("Deutsch", active = settings.language == "de") { viewModel.setLanguage("de") }
                    }
                }
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(20.dp))
        }

        if (!exactAlarmGranted) {
            item {
                OutlinedButton(
                    onClick = { ReminderScheduler.requestExactAlarmPermission(context) },
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                ) { Text(stringResource(R.string.settings_exact_notif)) }
            }
        }

        item {
            Text(
                stringResource(R.string.settings_test_notif),
                color = GtPrimaryDark, fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.sendTestNotification(context) }
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(999.dp))
                    .padding(13.dp),
            )
        }
    }

    val picker = openPicker
    if (picker != null) {
        val current = when (picker) {
            "breakfast" -> settings.reminderBreakfast
            "lunch" -> settings.reminderLunch
            "dinnerStart" -> settings.reminderDinnerStart
            else -> settings.reminderDinnerEnd
        }
        val (h, m) = ReminderScheduler.parseHourMinute(current)
        TimePickerDialog(
            initialHour = h,
            initialMinute = m,
            onDismiss = { openPicker = null },
            onConfirm = { hour, minute ->
                val value = "${hour.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
                when (picker) {
                    "breakfast" -> viewModel.updateReminderBreakfast(context, value)
                    "lunch" -> viewModel.updateReminderLunch(context, value)
                    "dinnerStart" -> viewModel.updateReminderDinnerStart(context, value)
                    else -> viewModel.updateReminderDinnerEnd(context, value)
                }
                openPicker = null
            },
        )
    }
}

@Composable
private fun LanguageChip(label: String, active: Boolean, onClick: () -> Unit) {
    Text(
        label,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = if (active) Color.White else GtOnSurfaceFaint,
        modifier = Modifier
            .clickable { onClick() }
            .background(if (active) GtPrimary else GtChipBg, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 7.dp),
    )
}

@Composable
private fun ReminderRow(label: String, value: String, showDivider: Boolean, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 13.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(label, fontSize = 13.5.sp, fontWeight = FontWeight.Medium)
            TimeChip(value, onClick)
        }
        if (showDivider) Divider(color = GtChipBg)
    }
}

@Composable
private fun TimeChip(value: String, onClick: () -> Unit) {
    Text(
        formatTime24to12(value),
        fontSize = 13.sp,
        modifier = Modifier
            .clickable { onClick() }
            .background(GtChipBg, RoundedCornerShape(10.dp))
            .padding(horizontal = 9.dp, vertical = 7.dp),
    )
}
