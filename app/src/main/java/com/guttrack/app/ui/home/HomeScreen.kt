package com.guttrack.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guttrack.app.R
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.MealType
import com.guttrack.app.data.model.SymptomEntry
import com.guttrack.app.ui.components.EntryRowFor
import com.guttrack.app.ui.components.SectionLabel
import com.guttrack.app.ui.components.buildDisplayList
import com.guttrack.app.ui.components.formatTime24to12
import com.guttrack.app.ui.theme.GtChipBg
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.viewmodel.GutTrackViewModel

@Composable
fun HomeScreen(viewModel: GutTrackViewModel) {
    val meals by viewModel.todayMeals.collectAsStateWithLifecycle()
    val symptoms by viewModel.todaySymptoms.collectAsStateWithLifecycle()
    val settings by viewModel.settings.collectAsStateWithLifecycle()

    val loggedTypes = meals.map { it.type }.toSet()
    val nextType = when {
        MealType.BREAKFAST.name !in loggedTypes -> MealType.BREAKFAST
        MealType.LUNCH.name !in loggedTypes -> MealType.LUNCH
        MealType.DINNER.name !in loggedTypes -> MealType.DINNER
        else -> null
    }
    val nextTime = when (nextType) {
        MealType.BREAKFAST -> formatTime24to12(settings.reminderBreakfast)
        MealType.LUNCH -> formatTime24to12(settings.reminderLunch)
        MealType.DINNER -> "${formatTime24to12(settings.reminderDinnerStart)} – ${formatTime24to12(settings.reminderDinnerEnd)}"
        else -> ""
    }

    val displayItems = buildDisplayList(meals, symptoms)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        item {
            Text(stringResource(R.string.tab_home), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp, bottom = 18.dp))
        }

        if (nextType != null) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.openLogMeal(nextType) }
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(28.dp, 28.dp, 28.dp, 8.dp))
                        .padding(20.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column {
                            Text(stringResource(R.string.next_up), fontSize = 11.sp, fontWeight = FontWeight.Bold, color = GtPrimaryDark, letterSpacing = 1.sp)
                            Text(stringResource(nextType.labelRes), fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
                            Text(nextTime, fontSize = 13.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 2.dp))
                        }
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(GtPrimary, RoundedCornerShape(20.dp, 20.dp, 8.dp, 20.dp)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(Icons.Filled.PhotoCamera, contentDescription = null, tint = Color.White)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .background(GtPrimary, RoundedCornerShape(999.dp))
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(stringResource(R.string.log_meal, stringResource(nextType.labelRes)), color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
                androidx.compose.foundation.layout.Spacer(Modifier.height(8.dp))
            }
        } else {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(stringResource(R.string.all_meals_logged), fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(stringResource(R.string.all_meals_logged_sub), fontSize = 12.5.sp, color = GtOnSurfaceFaint, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                MealChip(MealType.BREAKFAST, MealType.BREAKFAST.name in loggedTypes, viewModel, Modifier.weight(1f))
                MealChip(MealType.LUNCH, MealType.LUNCH.name in loggedTypes, viewModel, Modifier.weight(1f))
                MealChip(MealType.DINNER, MealType.DINNER.name in loggedTypes, viewModel, Modifier.weight(1f))
                MealChip(MealType.SNACK, false, viewModel, Modifier.weight(1f))
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.openSymptomNew() }
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(20.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Box(
                    modifier = Modifier.size(38.dp).background(Color(0xFF5468D4), RoundedCornerShape(13.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Filled.WaterDrop, contentDescription = null, tint = Color.White, modifier = Modifier.size(19.dp))
                }
                Text(stringResource(R.string.log_symptom), fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f))
                Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = GtOnSurfaceFaint)
            }
            androidx.compose.foundation.layout.Spacer(Modifier.height(10.dp))
        }

        item {
            SectionLabel(stringResource(R.string.logged_today, displayItems.size), modifier = Modifier.padding(bottom = 10.dp))
        }

        items(displayItems) { item ->
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
            androidx.compose.foundation.layout.Spacer(Modifier.height(4.dp))
        }
    }
}

@Composable
private fun MealChip(type: MealType, logged: Boolean, viewModel: GutTrackViewModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clickable { viewModel.openLogMeal(type) }
            .background(if (logged) GtChipBg else MaterialTheme.colorScheme.surface, RoundedCornerShape(16.dp))
            .padding(vertical = 11.dp, horizontal = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            stringResource(type.labelRes),
            fontSize = 10.5.sp,
            maxLines = 1,
            color = GtOnSurfaceFaint,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        if (logged) {
            Icon(Icons.Filled.CheckCircle, contentDescription = null, tint = GtPrimaryDark, modifier = Modifier.size(18.dp))
        } else {
            Text(if (type == MealType.SNACK) "+ Add" else "Log", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = GtPrimaryDark)
        }
    }
}
