package com.guttrack.app.ui.export

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guttrack.app.R
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.SymptomEntry
import com.guttrack.app.ui.components.EntryRowFor
import com.guttrack.app.ui.components.GtSwitch
import com.guttrack.app.ui.components.buildDisplayList
import com.guttrack.app.ui.theme.GtChipBg
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.viewmodel.ExportState
import com.guttrack.app.viewmodel.GutTrackViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ExportScreen(viewModel: GutTrackViewModel) {
    val meals by viewModel.exportMeals.collectAsStateWithLifecycle()
    val symptoms by viewModel.exportSymptoms.collectAsStateWithLifecycle()
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val today = LocalDate.now()
    val dayLabel = DateTimeFormatter.ofPattern("MMM d")
    val dates = (meals.map { it.dateEpoch } + symptoms.map { it.dateEpoch }).toSortedSet().sortedDescending()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {
        item {
            Text(stringResource(R.string.export_title), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp, bottom = 4.dp))
            Text(
                stringResource(R.string.export_desc),
                fontSize = 12.5.sp, color = GtOnSurfaceFaint, lineHeight = 18.sp,
                modifier = Modifier.padding(bottom = 12.dp),
            )
        }

        item {
            when (uiState.exportState) {
                ExportState.IDLE -> Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .clickable { viewModel.doExport(context) }
                        .background(GtPrimary, RoundedCornerShape(999.dp))
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(Icons.Filled.Mail, contentDescription = null, tint = Color.White, modifier = Modifier.size(19.dp).padding(end = 8.dp))
                    Text(stringResource(R.string.export_btn_email), color = Color.White, fontSize = 14.5.sp, fontWeight = FontWeight.Bold)
                }
                ExportState.SENDING -> Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .background(GtChipBg, RoundedCornerShape(999.dp))
                        .padding(15.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(stringResource(R.string.export_preparing), color = GtPrimaryDark, fontSize = 14.5.sp, fontWeight = FontWeight.Bold)
                }
                ExportState.SENT -> Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier.size(44.dp).background(GtPrimary, RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = Color.White)
                    }
                    Text(stringResource(R.string.export_sent_title), fontSize = 14.5.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
                    Text(stringResource(R.string.export_sent_sub), fontSize = 12.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 4.dp, bottom = 14.dp))
                    Text(
                        stringResource(R.string.export_done), color = GtPrimaryDark, fontSize = 13.sp, fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable { viewModel.resetExport() },
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                    .padding(16.dp),
            ) {
                Text(stringResource(R.string.export_preview_label), fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = GtPrimaryDark, letterSpacing = 1.sp)
                Text(stringResource(R.string.pdf_title), fontSize = 15.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp, bottom = 12.dp))

                for (epoch in dates) {
                    val date = LocalDate.ofEpochDay(epoch)
                    val label = when (date) {
                        today -> stringResource(R.string.day_today)
                        today.minusDays(1) -> stringResource(R.string.day_yesterday)
                        else -> date.format(dayLabel)
                    }
                    Text(
                        label.uppercase(), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GtOnSurfaceFaint,
                        modifier = Modifier.padding(top = 12.dp, bottom = 6.dp),
                    )
                    val dayMeals = meals.filter { it.dateEpoch == epoch }
                    val daySymptoms = symptoms.filter { it.dateEpoch == epoch }
                    val items = buildDisplayList(dayMeals, daySymptoms)
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        items.forEach { item ->
                            EntryRowFor(
                                item = item,
                                onEdit = {
                                    when (item) {
                                        is MealEntry -> viewModel.editMeal(item)
                                        is SymptomEntry -> viewModel.editSymptom(item)
                                    }
                                },
                                onDelete = {
                                    when (item) {
                                        is MealEntry -> viewModel.deleteMeal(item)
                                        is SymptomEntry -> viewModel.deleteSymptomEntry(item)
                                    }
                                },
                            )
                        }
                    }
                }
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(16.dp))
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp, vertical = 13.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(stringResource(R.string.export_include_photos), fontSize = 13.5.sp, fontWeight = FontWeight.SemiBold)
                    Text(stringResource(R.string.export_include_photos_sub), fontSize = 11.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 2.dp))
                }
                GtSwitch(checked = settings.includePhotosInExport, onCheckedChange = { viewModel.toggleIncludePhotos() })
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(12.dp))
        }
    }
}
