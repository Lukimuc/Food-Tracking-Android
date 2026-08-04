package com.guttrack.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.guttrack.app.R
import com.guttrack.app.data.model.MealEntry
import com.guttrack.app.data.model.SymptomEntry
import com.guttrack.app.ui.theme.GtChipBg
import com.guttrack.app.ui.theme.GtOnSurfaceDim
import com.guttrack.app.ui.theme.GtOnSurfaceFaint
import com.guttrack.app.ui.theme.GtPrimaryDark
import com.guttrack.app.ui.theme.severityColor

@Composable
fun Thumbnail(photoUri: String?, isSymptom: Boolean, size: androidx.compose.ui.unit.Dp = 48.dp) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(12.dp))
            .background(GtChipBg),
        contentAlignment = Alignment.Center,
    ) {
        when {
            photoUri != null -> AsyncImage(
                model = photoUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )
            isSymptom -> Icon(Icons.Filled.WaterDrop, contentDescription = null, tint = Color(0xFF5468D4), modifier = Modifier.size(size * 0.45f))
            else -> Icon(Icons.Filled.PhotoCamera, contentDescription = null, tint = Color(0xFF9397AB), modifier = Modifier.size(size * 0.45f))
        }
    }
}

@Composable
fun EntryRow(
    photoUri: String?,
    isSymptom: Boolean,
    label: String,
    time: String,
    note: String,
    drinkNote: String = "",
    severity: Int?,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .clickable { onEdit() },
        headlineContent = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                Text(time, fontSize = 11.sp, color = GtOnSurfaceFaint)
            }
        },
        supportingContent = {
            Column {
                if (note.isNotBlank()) {
                    Text(note, color = GtOnSurfaceDim, fontSize = 13.sp, modifier = Modifier.padding(top = 2.dp))
                }
                if (drinkNote.isNotBlank()) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)) {
                        Text(stringResource(R.string.entry_drink_label), fontWeight = FontWeight.Medium, fontSize = 12.sp, color = GtOnSurfaceFaint)
                        Text(drinkNote, color = GtOnSurfaceDim, fontSize = 12.sp)
                    }
                }
                if (severity != null) {
                    SeverityBadge(severity, severityColor(severity), modifier = Modifier.padding(top = 6.dp))
                }
            }
        },
        leadingContent = { Thumbnail(photoUri, isSymptom) },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = GtOnSurfaceFaint, modifier = Modifier.size(20.dp))
            }
        },
        colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
    )
}

@Composable
fun EntryRowFor(item: Any, onEdit: () -> Unit, onDelete: () -> Unit) {
    when (item) {
        is MealEntry -> EntryRow(
            photoUri = item.photoUris.split(",").firstOrNull(),
            isSymptom = false,
            label = stringResource(mealLabelRes(item)),
            time = item.time,
            note = item.note,
            drinkNote = "",
            severity = null,
            onEdit = onEdit,
            onDelete = onDelete,
        )
        is SymptomEntry -> EntryRow(
            photoUri = null,
            isSymptom = true,
            label = stringResource(R.string.log_symptom),
            time = item.time,
            note = item.note,
            drinkNote = "",
            severity = item.severity,
            onEdit = onEdit,
            onDelete = onDelete,
        )
    }
}
