package com.guttrack.app.ui.logmeal

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.guttrack.app.R
import com.guttrack.app.data.model.MealType
import com.guttrack.app.ui.components.TimePickerDialog
import com.guttrack.app.ui.components.formatTime24to12
import com.guttrack.app.ui.theme.GtChipBg
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.util.PhotoFiles
import com.guttrack.app.viewmodel.GutTrackViewModel
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LogMealScreen(viewModel: GutTrackViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    var pendingCaptureUri by remember { mutableStateOf<Uri?>(null) }
    var photoTarget by remember { mutableStateOf<String?>(null) }
    var newTagText by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }

    BackHandler { viewModel.closeModal() }

    val takePicture = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            pendingCaptureUri?.let { viewModel.onPhotoCaptured(it.toString()) }
        }
    }
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
        if (uri != null) {
            viewModel.onPhotoCaptured(uri.toString())
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).statusBarsPadding()) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(stringResource(R.string.log_meal, stringResource(uiState.logType.labelRes)), fontSize = 19.sp, fontWeight = FontWeight.Bold)
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(GtChipBg)
                        .clickable { viewModel.closeModal() },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.Close, contentDescription = "Close", tint = GtPrimaryDark)
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GtChipBg, RoundedCornerShape(999.dp))
                    .padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                MealType.entries.forEach { type ->
                    val selected = type == uiState.logType
                    Text(
                        stringResource(type.labelRes),
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (selected) Color.White else GtOnSurfaceFaint,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        modifier = Modifier
                            .weight(1f)
                            .clickable { viewModel.setLogType(type) }
                            .background(if (selected) GtPrimary else Color.Transparent, RoundedCornerShape(999.dp))
                            .padding(vertical = 9.dp),
                    )
                }
            }

            // Photo Section (Multi-picture Row)
            androidx.compose.foundation.lazy.LazyRow(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp, bottom = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(uiState.pendingPhotoUris.size) { index ->
                    val uri = uiState.pendingPhotoUris[index]
                    Box(modifier = Modifier.size(140.dp).clip(RoundedCornerShape(20.dp))) {
                        AsyncImage(
                            model = uri,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        // Remove button overlay
                        IconButton(
                            onClick = { viewModel.removePhoto(uri) },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(4.dp)
                                .size(28.dp)
                                .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(999.dp))
                        ) {
                            Icon(Icons.Filled.Close, contentDescription = "Remove", tint = Color.White, modifier = Modifier.size(16.dp))
                        }
                    }
                }
                item {
                    PhotoSlot(
                        label = "Add Photo",
                        uri = null,
                        isProcessing = uiState.isAiProcessing,
                        modifier = Modifier.size(140.dp),
                        onAction = { action ->
                            if (action == "camera") {
                                val uri = PhotoFiles.newCaptureUri(context)
                                pendingCaptureUri = uri
                                takePicture.launch(uri)
                            } else {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            }
                        }
                    )
                }
            }

            Text(stringResource(R.string.log_food_question), fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = GtOnSurfaceFaint, modifier = Modifier.padding(bottom = 6.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = uiState.noteText,
                    onValueChange = { viewModel.onNoteChange(it) },
                    placeholder = { Text(stringResource(R.string.log_food_placeholder)) },
                    modifier = Modifier.fillMaxWidth().height(140.dp),
                )
                if (uiState.isAiProcessing) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(horizontal = 4.dp).height(2.dp),
                        color = GtPrimary,
                    )
                }
            }
            Spacer(Modifier.height(20.dp))

            Text("Intolerances & Allergens", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = GtOnSurfaceFaint, modifier = Modifier.padding(bottom = 8.dp))
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                uiState.intoleranceTags.forEach { tag ->
                    AssistChip(
                        onClick = { viewModel.removeIntoleranceTag(tag) },
                        label = { Text(tag, fontSize = 11.sp) },
                        trailingIcon = { Icon(Icons.Filled.Close, contentDescription = null, modifier = Modifier.size(14.dp)) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFFFFEBEB),
                            labelColor = Color(0xFFD32F2F)
                        ),
                        border = null,
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = newTagText,
                    onValueChange = { newTagText = it },
                    placeholder = { Text("Add custom tag", fontSize = 11.sp) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true
                )
                
                // Manual AI Spark Button
                IconButton(
                    onClick = { viewModel.runManualIntoleranceCheck() },
                    modifier = Modifier.background(GtChipBg, RoundedCornerShape(12.dp)).size(44.dp)
                ) {
                    if (uiState.isAiProcessing && newTagText.isBlank()) {
                        CircularProgressIndicator(color = GtPrimary, modifier = Modifier.size(20.dp), strokeWidth = 2.dp)
                    } else {
                        Icon(Icons.Filled.AutoAwesome, contentDescription = "AI Suggest", tint = GtPrimary)
                    }
                }

                // Manual Add Button
                IconButton(
                    onClick = {
                        if (newTagText.isNotBlank()) {
                            viewModel.addIntoleranceTag(newTagText.trim())
                            newTagText = ""
                        }
                    },
                    modifier = Modifier.background(GtPrimary, RoundedCornerShape(12.dp)).size(44.dp)
                ) {
                    Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
                }
            }
            
            Spacer(Modifier.height(24.dp))

            // Custom Date/Time Row at the bottom
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.log_date_label), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GtOnSurfaceFaint)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .clickable { showDatePicker = true }
                            .background(GtChipBg, RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Text(uiState.logDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")), fontSize = 14.sp)
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(stringResource(R.string.log_time_label), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GtOnSurfaceFaint)
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                            .clickable { showTimePicker = true }
                            .background(GtChipBg, RoundedCornerShape(12.dp))
                            .padding(12.dp)
                    ) {
                        Text(formatTime24to12(uiState.logTime), fontSize = 14.sp)
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 14.dp)
                .clickable { viewModel.saveLog() }
                .background(GtPrimary, RoundedCornerShape(999.dp))
                .padding(14.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(stringResource(R.string.log_save), color = Color.White, fontSize = 14.5.sp, fontWeight = FontWeight.Bold)
        }
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = uiState.logDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    datePickerState.selectedDateMillis?.let {
                        viewModel.onDateChange(java.time.Instant.ofEpochMilli(it).atZone(ZoneOffset.UTC).toLocalDate())
                    }
                    showDatePicker = false
                }) { Text(stringResource(R.string.btn_ok)) }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text(stringResource(R.string.btn_cancel)) }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    if (showTimePicker) {
        val parts = uiState.logTime.split(":")
        TimePickerDialog(
            initialHour = parts[0].toInt(),
            initialMinute = parts[1].toInt(),
            onDismiss = { showTimePicker = false },
            onConfirm = { h, m ->
                viewModel.onTimeChange("${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}")
                showTimePicker = false
            }
        )
    }
}

@Composable
private fun PhotoSlot(
    label: String,
    uri: String?,
    isProcessing: Boolean,
    modifier: Modifier = Modifier,
    onAction: (String) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .height(140.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(GtChipBg)
            .clickable { showMenu = true },
        contentAlignment = Alignment.Center,
    ) {
        if (uri != null) {
            AsyncImage(
                model = uri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Filled.PhotoCamera, contentDescription = null, tint = GtOnSurfaceFaint, modifier = Modifier.size(24.dp))
                Text(label, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 4.dp))
            }
        }

        if (isProcessing) {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {
                androidx.compose.material3.CircularProgressIndicator(color = Color.White, modifier = Modifier.size(28.dp), strokeWidth = 3.dp)
            }
        }

        DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.log_take_photo)) },
                onClick = { showMenu = false; onAction("camera") },
            )
            DropdownMenuItem(
                text = { Text(stringResource(R.string.log_choose_gallery)) },
                onClick = { showMenu = false; onAction("gallery") },
            )
        }
    }
}
