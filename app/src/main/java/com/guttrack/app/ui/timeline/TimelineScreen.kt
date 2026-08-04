package com.guttrack.app.ui.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.guttrack.app.R
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.SymptomEntry
import com.guttrack.app.ui.components.EntryRowFor
import com.guttrack.app.ui.components.buildDisplayList
import com.guttrack.app.ui.theme.GtOnSurfaceDim
import com.guttrack.app.ui.theme.GtPrimary
import com.guttrack.app.viewmodel.GutTrackViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun TimelineScreen(viewModel: GutTrackViewModel) {
    val selectedDate by viewModel.selectedTimelineDate.collectAsStateWithLifecycle()
    val meals by viewModel.timelineMeals.collectAsStateWithLifecycle()
    val symptoms by viewModel.timelineSymptoms.collectAsStateWithLifecycle()
    val displayItems = buildDisplayList(meals, symptoms)

    val today = LocalDate.now()
    val todayLabel = stringResource(R.string.day_today)
    val yesterdayLabel = stringResource(R.string.day_yesterday)
    val dayOptions = (0..6).map { offset ->
        val date = today.minusDays(offset.toLong())
        val label = when (offset) {
            0 -> todayLabel
            1 -> yesterdayLabel
            else -> date.format(DateTimeFormatter.ofPattern("MMM d"))
        }
        date to label
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 24.dp),
    ) {
        item {
            Text("Timeline", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 8.dp, bottom = 14.dp))
        }
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 18.dp),
            ) {
                items(dayOptions) { (date, label) ->
                    val active = date == selectedDate
                    Text(
                        label,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (active) Color.White else GtOnSurfaceDim,
                        modifier = Modifier
                            .clickable { viewModel.selectTimelineDate(date) }
                            .background(if (active) GtPrimary else com.guttrack.app.ui.theme.GtChipBg, RoundedCornerShape(999.dp))
                            .padding(horizontal = 16.dp, vertical = 9.dp),
                    )
                }
            }
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
